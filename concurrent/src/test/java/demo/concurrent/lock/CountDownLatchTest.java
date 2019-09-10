package demo.concurrent.lock;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 自己实现的栅栏，测试
 *
 * @author leishiguang
 * @since v1.0
 */
class CountDownLatchTest {

    @Test
    void go() {
        int n = 10;
        // 创建计数为 10
        CountDownLatch myCountDownLatch = new CountDownLatch(n);
        for (int i = 0; i < n; i++) {
            String name = "NO." + i;
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "\t执行完毕，name:" + name);
                // 参与计数
                myCountDownLatch.countDown();
            });
        }
        // 等待所有线程执行完毕
        myCountDownLatch.await();
        System.out.println("所有线程执行完毕，召唤神龙");
    }
}