package demo.concurrent.lock;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 自己实现的独享锁，测试
 *
 * @author leishiguang
 * @since v1.0
 */
class ExclusiveLockTest {

    private int i = 0;
    private Lock myLock = new ExclusiveLock();

    private void add() {
        myLock.lock();
        try {
            i++;
        } finally {
            myLock.unlock();
        }
    }

    @Test
    void go() throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
                for (int j = 0; j < 10000; j++) {
                    this.add();
                }
            });
        }
        Thread.sleep(2000L);
        assertEquals(40000, i);
    }
}