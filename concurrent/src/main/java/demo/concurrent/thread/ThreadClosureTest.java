package demo.concurrent.thread;

import demo.common.ThreadPoolUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 线程封闭示例
 *
 * @author leishiguang
 * @version 1.0
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("线程封闭")
class ThreadClosureTest {

    /**
     * threadLocal变量，每个线程都有一个副本，互不干扰
     */
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @AfterAll
    void afterClass(){
        threadLocal.remove();
    }

    @Test
    @DisplayName("公共变量的方式")
    void test1() throws InterruptedException {
        threadLocal.set("主线程设置的ThreadLocal值123");
        System.out.println(String.format("主线程，执行前，ThreadLocal的值：%s",threadLocal.get()));
        ThreadPoolUtils.getInstance().getExecutorService().execute(()->{
            assertNull(threadLocal.get());
            System.out.println(String.format("线程1，执行前，ThreadLocal的值：%s",threadLocal.get()));
            threadLocal.set("主线程设置的ThreadLocal值456");
            System.out.println(String.format("线程1，执行后，ThreadLocal的值：%s",threadLocal.get()));
        });
        Thread.sleep(1000L);
        System.out.println(String.format("主线程，执行后，ThreadLocal的值：%s",threadLocal.get()));
    }

    @Test
    @DisplayName("私有变量")
    void test2() throws InterruptedException {
        ThreadLocal<String> param1 = new ThreadLocal<>();
        ThreadLocal<String> param2 = new ThreadLocal<>();
        param1.set("主线程ThreadLocal的值1");
        param2.set("主线程ThreadLocal的值2");
        System.out.println(String.format("主线程，执行前，ThreadLocal的值：%s，%s",param1.get(),param2.get()));
        ThreadPoolUtils.getInstance().getExecutorService().execute(()->{
            System.out.println(String.format("子线程，执行前，ThreadLocal的值：%s，%s",param1.get(),param2.get()));
            assertNull(param1.get());
            assertNull(param2.get());
            param1.set("子线程ThreadLocal的值1");
            param2.set("子线程ThreadLocal的值2");
            System.out.println(String.format("子线程，执行后，ThreadLocal的值：%s，%s",param1.get(),param2.get()));
            assertEquals("子线程ThreadLocal的值1",param1.get());
            assertEquals("子线程ThreadLocal的值2",param2.get());
        });
        Thread.sleep(1000L);
        System.out.println(String.format("主线程，执行后：%s，%s",param1.get(),param2.get()));
        assertEquals("主线程ThreadLocal的值1",param1.get());
        assertEquals("主线程ThreadLocal的值2",param2.get());
    }
}
