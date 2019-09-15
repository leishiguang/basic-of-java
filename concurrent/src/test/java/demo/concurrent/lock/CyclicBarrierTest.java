package demo.concurrent.lock;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 自己实现的循环栅栏，测试
 *
 * @author leishiguang
 * @since v1.0
 */
class CyclicBarrierTest {

    @Test
    void go() throws InterruptedException {
        // 等待队列
        LinkedBlockingQueue<String> waitQueue = new LinkedBlockingQueue<>();
        int threadCount = 4;
        int maxThreadCount = 100;

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount, new Runnable() {
            @Override
            public void run() {
                String treadName = Thread.currentThread().getName();
                System.out.println("有" + threadCount + "个线程执行了，发车~" + treadName);
                for (int i = 0; i < threadCount; i++) {
                    System.out.println(treadName + "执行出栈：" + waitQueue.poll());
                }
            }
        });

        for (int i = 0; i < maxThreadCount; i++) {
            String countString = String.valueOf(i);
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                //将数据对象缓存起来
                try {
                    waitQueue.put(countString);
                    //模拟数据库耗时
                    Thread.sleep(new Random().nextInt(200));
                    //等待栅栏打开
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(4000L);

    }
}