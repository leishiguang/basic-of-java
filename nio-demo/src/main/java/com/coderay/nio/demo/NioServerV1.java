package com.coderay.nio.demo;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 缓存了已建立连接的channel，用一个线程应对多个客户端的同时连接。
 */
public class NioServerV1 {

    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = NioUtilTool.initServerChannel();
        while (true){
            // 获取新的 tcp 连接通道
            SocketChannel socketChannelTmp = serverSocketChannel.accept();
            // 接收到 tcp 请求，需要读取/响应
            if(socketChannelTmp != null){
                System.out.println("收到新连接：" + socketChannelTmp.getRemoteAddress());
                //设置为非阻塞
                socketChannelTmp.configureBlocking(false);
                channels.add(socketChannelTmp);
            }
            Iterator<SocketChannel> iterator = channels.iterator();
            while (iterator.hasNext()){
                SocketChannel socketChannel = iterator.next();
                ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                //该通道没有数据要处理
                if(socketChannel.read(requestBuffer) == 0){
                    continue;
                }
                //读取数据
                NioUtilTool.readBufferFormChannel(socketChannel,requestBuffer);
                //打印缓冲区的数据
                NioUtilTool.printBuffer(requestBuffer);
                //返回响应消息
                NioUtilTool.responseMessageToChannel(socketChannel);
                //移除
                iterator.remove();
            }
        }
    }


}
