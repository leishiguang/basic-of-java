package com.coderay.concurrent.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.LockSupport;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 三种线程协作通信的方式：suspend/resume、wait/notify、park/unpark
 * date 2019/04/18
 * @author leishiguang
 */
@DisplayName("线程通信")
class ThreadSignal {

    /**
     * 包子店
     */
    private static Object baozidian = null;

    @BeforeEach
    void clearBaozidian() {
        baozidian = null;
    }

    /**
     * 正常的suspend/resume
     */
    @Test
    @DisplayName("正常的suspend/resume")
    void suspendResumeTest() throws Exception {
        // 启动买包子线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者");
    }

    /**
     * 死锁的suspend/resume
     * suspend并不会像wait一样释放锁，故此容易写出死锁代码
     */
    @Disabled
    @Test
    @DisplayName("死锁的suspend/resume")
    void suspendResumeDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            consumerThread.resume();
        }
        System.out.println("3、通知消费者");
    }

    /**
     * 导致程序永久挂起的suspend/resume
     */
    @Disabled
    @Test
    @DisplayName("导致程序永久挂起的suspend/resume")
    void suspendResumeDeadLockTest2() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("1、没包子，进入等待");
                try { // 为这个线程加上一点延时
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里的挂起执行在resume后面
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 这时候的 consumer 线程，还没有 suspend 。
        consumerThread.resume();
        System.out.println("3、通知消费者");
        consumerThread.join();
    }


    /**
     * 正常的wait/notify
     */
    @Test
    @DisplayName("正常的wait/notify")
    void waitNotifyTest() throws Exception {
        // 启动线程
        new Thread(() -> {
            synchronized (this) {
                while (baozidian == null) { // 如果没包子，则进入等待
                    try {
                        System.out.println("1、进入等待");
                        this.wait(); //这次的等待，会自动释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /**
     * 会导致程序永久等待的wait/notify
     */
    @Disabled
    @Test
    @DisplayName("会导致程序永久等待的wait/notify")
    void waitNotifyDeadLockTest() throws Exception {
        // 启动线程
        new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("1、进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        synchronized (this) {
            // 这次的 notify，子线程还没有执行 wait
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /**
     * 正常的park/unpark
     */
    @Test
    @DisplayName("正常的park/unpark")
    void parkUnparkTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                LockSupport.park();
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3、通知消费者");
    }

    /**
     * 死锁的park/unpark
     */
    @Disabled
    @Test
    @DisplayName("死锁的park/unpark")
    void parkUnparkDeadLockTest() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) { // 如果没包子，则进入等待
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    LockSupport.park();
                }
            }
            System.out.println("2、买到包子，回家");
            assertNotNull(baozidian);
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(3000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3、通知消费者");
    }
}
