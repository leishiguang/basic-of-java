package com.coderay.concurrent.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * 线程封闭示例
 *
 */
@DisplayName("线程封闭")
class ThreadClosure {

    /**
     * threadLocal变量，每个线程都有一个副本，互不干扰
     */
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    @DisplayName("公共变量的方式")
    void test1() throws InterruptedException {
        threadLocal.set("主线程123");
        System.out.println(String.format("主线程，执行前：%s",threadLocal.get()));
        new Thread(()->{
            assertNull(threadLocal.get());
            System.out.println(String.format("线程1，执行前：%s",threadLocal.get()));
            threadLocal.set("子线程设置值456");
            System.out.println(String.format("线程1，执行后：%s",threadLocal.get()));
        }).start();
        Thread.sleep(1000L);
        System.out.println(String.format("主线程，执行后：%s",threadLocal.get()));
    }

    @Test
    @DisplayName("私有变量")
    void test2() throws InterruptedException {
        ThreadLocal<String> parm1 = new ThreadLocal<>();
        ThreadLocal<String> parm2 = new ThreadLocal<>();
        parm1.set("主线程1");
        parm2.set("主线程2");
        System.out.println(String.format("主线程，执行前：%s，%s",parm1.get(),parm2.get()));
        new Thread(()->{
            System.out.println(String.format("子线程，执行前：%s，%s",parm1.get(),parm2.get()));
            assertNull(parm1.get());
            assertNull(parm2.get());
            parm1.set("子线程1");
            parm2.set("子线程2");
            System.out.println(String.format("子线程，执行后：%s，%s",parm1.get(),parm2.get()));
            assertEquals("子线程1",parm1.get());
            assertEquals("子线程2",parm2.get());
        }).start();
        Thread.sleep(1000L);
        System.out.println(String.format("主线程，执行后：%s，%s",parm1.get(),parm2.get()));
        assertEquals("主线程1",parm1.get());
        assertEquals("主线程2",parm2.get());
    }
}
