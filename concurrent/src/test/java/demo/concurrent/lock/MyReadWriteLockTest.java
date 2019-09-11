package demo.concurrent.lock;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 自己实现的读写锁，测试
 * 读写锁（既保证了读数据的效率，也保证数据的一致性）
 * 如果写锁当前被线程 T1 占用，那么其他线程不能再进行任何锁定，除非是 T1 线程自身
 *
 * @author leishiguang
 * @since v1.0
 */
class MyReadWriteLockTest {

    private ReadWriteLock myRwl = new MyReadWriteLock();
    private String sharedString = "hello";

    @Test
    void go() throws InterruptedException {
        //所有线程可以同时读
        for (int i = 0; i < 5; i++) {
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                myRwl.readLock().lock();
                try {
                    System.out.println("allThreadCanRead:" + sharedString);
                } finally {
                    myRwl.readLock().unlock();
                }
            });
        }
        //有写锁的时候只有当前线程可以读
        for (int i = 0; i < 5; i++) {
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                myRwl.writeLock().lock();
                try {
                    sharedString = Thread.currentThread().toString();
                    System.out.println(Thread.currentThread() + ":write," + sharedString);
                    Thread.sleep(new Random().nextInt(100));
                    myRwl.readLock().lock();
                    try {
                        System.out.println(Thread.currentThread() + ":read:" + sharedString);
                        Thread.sleep(new Random().nextInt(100));
                    } finally {
                        myRwl.readLock().unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    myRwl.writeLock().unlock();
                }
            });
        }
        Thread.sleep(3000L);
    }
}