package demo.netty.push;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 自定义的一个服务
 */
public class XNettyServer {

    public static void main(String[] args) throws Exception {
        //1、线程定义
        //accept 处理连接的线程池
        EventLoopGroup acceptGroup = new NioEventLoopGroup();
        //read io 处理数据的线程池
        EventLoopGroup readGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(acceptGroup, readGroup);
            //2、选择TCP协议，NIO的方式
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch){
                    //3、职责链定义（请求收到后怎么处理）
                    ChannelPipeline pipeline = ch.pipeline();
                    //3.1、增加解码器
                    pipeline.addLast(new XDecoder());
                    //3.2、打印出内容
                    pipeline.addLast(new Xhandller());
                }
            });
            //4、绑定端口，开始服务
            System.out.println("启动成功，端口9999");
            b.bind(9999).sync().channel().closeFuture().sync();
        }finally {
            acceptGroup.shutdownGracefully();
            readGroup.shutdownGracefully();
        }
    }
}
