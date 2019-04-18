package com.coderay.nio.demo;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * NIO 的客户端
 */
public class NioClient {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8080));
        // 没有链接上就要一直等待
        while (!socketChannel.finishConnect()){
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要发送给服务器的数据：");
        String msg = scanner.nextLine();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()){
            socketChannel.write(buffer);
        }
        // 收到服务器响应
        System.out.println("等待服务器响应：");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        NioUtilTool.readBufferFormChannel(socketChannel,requestBuffer);
        NioUtilTool.printBuffer(requestBuffer);

    }
}
