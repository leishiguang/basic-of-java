package demo.nio.demo;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 使用 reactor 事件通知实现的 nio server，单线程
 */
public class NioServerV2 {


    public static void main(String[] args) throws Exception{

        //1、创建网络服务端 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        //2、构建一个Selector选择器，将channel注册上去
        Selector selector = Selector.open();
        //将serverSocketChannel注册到selector
        SelectionKey selectionKey = serverSocketChannel.register(selector,0,serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        //3、绑定端口并启动
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("Server start success");

        while (true){
            //select 方法具有阻塞效果，直到有事件通知才会返回
            selector.select();
            //获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            //遍历查询结果
            Iterator<SelectionKey> iter = selectionKeys.iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                iter.remove();
                if(key.isAcceptable()){
                    NioUtilTool.selectorAcceptable(key,selector);
                }
                if(key.isReadable()){
                    NioUtilTool.selectorReadable(key);
                }

            }
            selector.selectNow();
        }
    }



}
