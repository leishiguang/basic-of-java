package com.coderay.nio;

import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class ReactorThread extends Thread {

    /**
     * 每个线程具有自己的选择器，以区分不同的监听事件
     */
    private Selector selector;

    /**
     * 线程的工作队列
     */
    private LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<>();

    /**
     * 表明是否已经正在工作
     */
    private volatile boolean running = false;

    /**
     * Select 监听到有事件之后，调用这个方法
     */
    public abstract void handler(SelectableChannel channel) throws Exception;

    ReactorThread() throws Exception {
        selector = Selector.open();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Runnable task;
                while ((task = taskQueue.poll()) != null) {
                    task.run();
                }
                selector.select(1000);
                //获取查询结果
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                //遍历查询结果
                Iterator<SelectionKey> iter = selectionKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    int readyOps = key.readyOps();
                    // 关注 Read 和 Accept 两个事件
                    if ((readyOps & (SelectionKey.OP_READ | SelectionKey.OP_ACCEPT)) != 0 || readyOps == 0) {
                        try {
                            SelectableChannel channel = (SelectableChannel) key.attachment();
                            channel.configureBlocking(false);
                            //调用抽象方法执行业务逻辑
                            handler(channel);
                            if (!channel.isOpen()) {
                                key.cancel(); // 如果关闭了,就取消这个KEY的订阅
                            }
                        } catch (Exception ex) {
                            key.cancel(); // 如果有异常,就取消这个KEY的订阅
                        }
                    }
                }
                selector.selectNow();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将某个通道注册到当前的选择器，返回的 SelectionKey 可以用于注册感兴趣的事件。
     * 调用 IO 线程的 register 时，关注 Read 事件；
     * 调用 Main 线程的 reister 时，关注 Accept 事件；
     */
    SelectionKey register(SelectableChannel channel) throws Exception {
        // 为什么register要以任务提交的形式，让reactor线程去处理？
        // 因为线程在执行channel注册到selector的过程中，会和调用selector.select()方法的线程争用同一把锁
        // 而select()方法实在eventLoop中通过while循环调用的，争抢的可能性很高，为了让register能更快的执行，就放到同一个线程来处理
        FutureTask<SelectionKey> futureTask = new FutureTask<>(() -> channel.register(selector, 0, channel));
        taskQueue.add(futureTask);
        return futureTask.get();
    }

    /**
     * 启动当前线程的run方法，不断循环的接受工作。
     */
    void doStart() {
        if (!running) {
            running = true;
            super.start();
        }
    }
}
