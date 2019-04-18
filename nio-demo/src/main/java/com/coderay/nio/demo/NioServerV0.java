package com.coderay.nio.demo;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 最简单的 NIO 服务，直接基于非阻塞的写法。
 * 在应对多个 client 连接的时候，只会处理一个，无法并发处理。
 */
public class NioServerV0 {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = NioUtilTool.initServerChannel();
        while (true){
            // 获取新的 tcp 连接通道
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 接收到 tcp 请求，需要读取/响应
            if(socketChannel != null){
                System.out.println("收到新连接：" + socketChannel.getRemoteAddress());
                //设置为非阻塞【为什么要设置两次？】
                socketChannel.configureBlocking(false);
                ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                //将 socket 中的数据读到缓冲区
                NioUtilTool.readBufferFormChannel(socketChannel,requestBuffer);
                //如果没有读到数据，就不进行返回
                if(requestBuffer.position() == 0){
                    continue;
                }
                //打印缓冲区的数据
                NioUtilTool.printBuffer(requestBuffer);
                //响应结果
                NioUtilTool.responseMessageToChannel(socketChannel);
            }
        }
    }
}
