package demo.nio.demo;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 只负责处理 serverSocketChannel 的线程
 */
public class MainReactorThread extends ReactorThread{

    private AtomicInteger incr = new AtomicInteger(0);
    private ReactorThread[] subReactorThreads;

    MainReactorThread(ReactorThread[] subReactorThreads) throws Exception {
        super();
        this.subReactorThreads = subReactorThreads;
    }

    @Override
    public void handler(SelectableChannel channel) throws Exception {
        // 只做请求分发，不做具体的数据读取
        ServerSocketChannel ch = (ServerSocketChannel)channel;
        SocketChannel socketChannel = ch.accept();
        socketChannel.configureBlocking(false);
        //分发给新的IO线程去读取数据...
        int index = incr.getAndIncrement() % subReactorThreads.length;
        ReactorThread workEventLoop = subReactorThreads[index];
        //让 IO 线程工作起来
        workEventLoop.doStart();
        //将当前连接注册到 IO 线程，并添加感兴趣的事件（READ）。这样，当客户端发来了数据时，IO 线程就会自动调用业务逻辑方法做操作...
        SelectionKey selectionKey = workEventLoop.register(socketChannel);
        selectionKey.interestOps(SelectionKey.OP_READ);
        System.out.println(currentThread().getName() + "收到新连接 : " + socketChannel.getRemoteAddress());
    }
}
