package demo.nio.demo;

import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * IO 线程
 */
public class SubReactorThread extends ReactorThread{

    private ExecutorService workPool;
    SubReactorThread(ExecutorService workPool) throws Exception{
        super();
        this.workPool = workPool;
    }

    @Override
    public void handler(SelectableChannel channel) throws Exception {
        SocketChannel ch = (SocketChannel) channel;
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        //读取数据
        NioUtilTool.readBufferFormChannel(ch,requestBuffer);
        //如果没有数据，就不进行操作
        if(requestBuffer.position() != 0){
            //打印收到的消息
            NioUtilTool.printBuffer(requestBuffer);
            workPool.submit(()->{
                // 业务操作：数据库、接口……
            });
            //响应结果
            NioUtilTool.responseMessageToChannel(ch);
        }
    }
}
