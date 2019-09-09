package demo.nio.demo;

import java.io.IOException;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * Nio实现访问百度网页，打印出百度服务器返回数据的 Server 信息
 */
public class NioHttpClient {

    public static void main(String[] args) throws IOException {
        String hostName = "www.baidu.com";
        String httpContext = "GET / HTTP/1.1\n\n";
        String qqNumber = "610151240";
        String requestKey = "Server";
        String resultStringFormat = "我的QQ号：%s，我的解析到百度服务器server类型是：%s";
        SocketAddress socketAddress = hostNameToSocketAddress(hostName);
        SocketChannel socketChannel = initSocketChannel(socketAddress);
        writeBufferToChannel(socketChannel, httpContext);
        ByteBuffer requestBuffer = readBufferFormChannel(socketChannel);
        String httpRequestString = bufferToString(requestBuffer);
        String requestValue = selectKeyStrFromHttpRequestStr(httpRequestString, requestKey);
        System.out.println(String.format(resultStringFormat, qqNumber, requestValue));
    }

    /**
     * 初始化一个已打开的 SocketChannel
     *
     * @param remote SocketAddress
     * @return SocketChannel 返回初始化好的通道
     * @throws IOException If some other I/O error occurs
     */
    private static SocketChannel initSocketChannel(SocketAddress remote) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(remote);
        while (!socketChannel.finishConnect()) {
            Thread.yield();
        }
        return socketChannel;
    }

    /**
     * 将域名转化成 SocketAddress
     *
     * @param hostName 域名
     * @return SocketAddress 转化之后的域名，默认 80 端口
     * @throws UnknownHostException 无法从 hostName 获取 ip
     */
    private static SocketAddress hostNameToSocketAddress(String hostName) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName(hostName);
        return new InetSocketAddress(addr, 80);
    }

    /**
     * 往通道中写入数据
     *
     * @param socketChannel 已经连接的通道
     * @param str           要写入的字符串
     * @throws IOException socketChannel.write 写入异常
     */
    private static void writeBufferToChannel(SocketChannel socketChannel, String str) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes(StandardCharsets.UTF_8));
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    /**
     * 如果没有读到数据，就会阻塞，直到读到为止
     *
     * @param socketChannel socket通道
     * @return ByteBuffer 从通道中读取到数据之后的 byteBuffer
     * @throws IOException socketChannel.read 读取错误
     */
    private static ByteBuffer readBufferFormChannel(SocketChannel socketChannel) throws IOException {
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        //以read的方式将数据读取到缓冲区
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            //长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (requestBuffer.position() > 0) {
                //System.out.println("从 channel 中获取到了数据");
                break;
            }
        }
        return requestBuffer;
    }

    /**
     * 从 ByteBuffer 中读取为 String
     *
     * @param requestBuffer 要读取的 buffer
     * @return String 读取到的 String
     */
    private static String bufferToString(ByteBuffer requestBuffer) {
        //开始读取数据
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        return new String(content, StandardCharsets.UTF_8);
    }

    /**
     * 从 Http 协议格式的 String 中，获取关键值
     *
     * @param httpRequestStr http 协议格式的 String
     * @param key            关键值的开头
     * @return String 筛选出的 value
     * @throws IOException 无法获取到对应 key
     */
    private static String selectKeyStrFromHttpRequestStr(String httpRequestStr, String key) throws IOException {
        if ("".equals(key) || key == null) {
            throw new NullPointerException("the key is null");
        }
        String[] strings = httpRequestStr.split("\n");
        String value = "";
        for (String str : strings) {
            if (str.startsWith(key)) {
                value = str;
                break;
            }
        }
        if ("".equals(value)) {
            throw new IOException(String.format("can't found '%s' key from requestString", key));
        }
        return value;
    }
}
