package demo.concurrent.lock.aqs;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 自己实现的 AQS 抽线队列同步器
 * acquire、 acquireShared ： 定义了资源争用的逻辑，如果没拿到，则等待。
 * tryAcquire、 tryAcquireShared ： 实际执行占用资源的操作，如何判定一个由使用者具体去实现。
 * release、 releaseShared ： 定义释放资源的逻辑，释放之后，通知后续节点进行争抢。
 * tryRelease、 tryReleaseShared： 实际执行资源释放的操作，具体的AQS使用者去实现。
 *
 * @author leishiguang
 * @since v1.0
 */
public class AbstractQueuedSynchronizer {

    /**
     * 当前资源的拥有者
     */
    protected volatile AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 保存当前正在等待的线程
     */
    private volatile LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    /**
     * 记录资源的状态
     */
    private volatile AtomicInteger state = new AtomicInteger(0);

    /**
     * 执行资源争抢
     *
     * @return 是否抢到了资源
     */
    public boolean tryAcquire(){
        throw new UnsupportedOperationException("由子类实现");
    }

    /**
     * 共享资源占用的逻辑，返回资源占用的情况
     */
    public int tryAcquireShared(){
        throw new UnsupportedOperationException("由子类实现");
    }

    /**
     * 执行资源释放
     * @return 释放成功
     */
    public boolean tryRelease(){
        throw new UnsupportedOperationException("由子类实现");
    }

    /**
     * 执行共享资源释放
     * @return 释放成功
     */
    public boolean tryReleaseShared(){
        throw new UnsupportedOperationException("由子类实现");
    }

    /**
     * 尝试进行资源争抢，如果没抢到，就进入等待队列
     */
    public void acquire() {
        //第一次执行争抢
        boolean firstAddQueue = true;
        // 不断尝试增抢资源
        while (!tryAcquire()) {
            if (firstAddQueue) {
                //如果没有抢到，加入等待集合
                waiters.offer(Thread.currentThread());
                firstAddQueue = false;
            } else {
                //已经在等待队列了，挂起当前线程
                LockSupport.park();
            }
        }
        // 如果抢到了，从等待队列中移除
        waiters.remove(Thread.currentThread());
    }

    /**
     *
     */
    public void acquireShared(){
        //第一次执行争抢
        boolean firstAddQueue = true;
        // 不断尝试增抢资源
        while (tryAcquireShared() < 0) {
            if (firstAddQueue) {
                //如果没有抢到，加入等待集合
                waiters.offer(Thread.currentThread());
                firstAddQueue = false;
            } else {
                //已经在等待队列了，挂起当前线程
                LockSupport.park();
            }
        }
        // 如果抢到了，从等待队列中移除
        waiters.remove(Thread.currentThread());
    }


    /**
     * 当资源被释放的时候，要通知等待队列的线程，继续争抢
     */
    public void release() {
        if (tryRelease()) {
            //通知所有等待者
            for (Thread next : waiters) {
                //唤醒线程
                LockSupport.unpark(next);
            }
        }
    }

    public void releaseShared(){
        if (tryReleaseShared()) {
            //通知所有等待者
            for (Thread next : waiters) {
                //唤醒线程
                LockSupport.unpark(next);
            }
        }
    }

    /**
     * 获取资源状态
     */
    public AtomicInteger getState(){
        return state;
    }


}
