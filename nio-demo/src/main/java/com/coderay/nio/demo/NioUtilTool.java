package com.coderay.nio.demo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 一些公共的方法
 */
class NioUtilTool {

    /**
     * 初始化 serverchannel
     */
    static ServerSocketChannel initServerChannel() throws Exception {
        //创建网络服务
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("server start success");
        return serverSocketChannel;
    }

    /**
     * 没有读到数据，就会一直循环读，直到读到为止
     */
    static void readBufferFormChannel(SocketChannel socketChannel, ByteBuffer requestBuffer) throws Exception {
        //以read的方式将数据读取到缓冲区
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            //长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (requestBuffer.position() > 0) {
                System.out.println("从 channel 中获取到了数据");
                break;
            }
        }
    }

    static void printBuffer(ByteBuffer requestBuffer) {
        //开始读取数据
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println(String.format("%s 收到数据，内容为：%s", Thread.currentThread().getName(), new String(content)));
    }

    static void responseMessageToChannel(SocketChannel socketChannel) throws Exception {
        //响应结果
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Length: 11\r\n\r\n" +
                "Hello World";
        ByteBuffer responseBuffer = ByteBuffer.wrap(response.getBytes());
        //因为是非阻塞，所以要循环写，确保写入完整
        while (responseBuffer.hasRemaining()) {
            socketChannel.write(responseBuffer);
        }
        System.out.println(String.format("已响应回执，发送给 %s", socketChannel.getRemoteAddress()));
    }

    /**
     * 监听到连接事件
     */
    static void selectorAcceptable(SelectionKey key, Selector selector) throws Exception{
        ServerSocketChannel server = (ServerSocketChannel) key.attachment();
        //将拿到的客户端连接通道，注册到select上
        SocketChannel clientSocketChannel = server.accept();
        clientSocketChannel.configureBlocking(false);
        clientSocketChannel.register(selector,SelectionKey.OP_READ,clientSocketChannel);
        System.out.println("收到新连接："+clientSocketChannel.getRemoteAddress());
    }

    /**
     * 监听到读事件
     */
    static void selectorReadable(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.attachment();
            ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
            //获取缓冲区数据
            NioUtilTool.readBufferFormChannel(socketChannel, requestBuffer);
            //打印缓冲区的数据
            NioUtilTool.printBuffer(requestBuffer);
            //返回响应消息
            NioUtilTool.responseMessageToChannel(socketChannel);
        } catch (Exception e) {
            //取消时间订阅
            key.cancel();
        }
    }
}
