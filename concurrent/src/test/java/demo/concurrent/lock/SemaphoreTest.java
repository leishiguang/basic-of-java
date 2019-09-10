package demo.concurrent.lock;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 自己实现的信号量，测试
 *
 * @author leishiguang
 * @since v1.0
 */
class SemaphoreTest {

    /**
     * 信号量，只允许 5 个线程同时访问
     */
    private Semaphore mySemaphore = new Semaphore(5);

    @Test
    void go() throws InterruptedException {
        // 信号总数
        int n = 9;
        for (int i = 0; i < n; i++) {
            String name = "NO." + i;
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                // 获取令牌
                mySemaphore.acquire();
                this.service(name);
                mySemaphore.release();
            });
        }
        // 等待所有线程执行完
        Thread.sleep(30000L);
    }

    /**
     * 限流，只允许 5 个线程同时访问
     */
    private void service(String name) {
        System.out.println("当前访问的用户，name：" + name);
        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("用户访问结束，name：" + name);
    }
}