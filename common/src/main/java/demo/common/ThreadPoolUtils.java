package demo.common;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Getter;

import java.util.concurrent.*;

/**
 * 线程池的工具类，返回一个线程池
 * 单例模式实现
 *
 * @author leishiguang
 * @since v1.0
 */
public class ThreadPoolUtils {

    private static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
            0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadFactoryBuilder().setNameFormat("common-%d").build());

    private ThreadPoolUtils() {
    }

    public static ThreadPoolUtils getInstance() {
        return SingletonLazyHolder.THREAD_POOL_UTILS;
    }

    public ExecutorService getExecutorService(){
        return EXECUTOR_SERVICE;
    }

    private static class SingletonLazyHolder {
        private static final ThreadPoolUtils THREAD_POOL_UTILS = new ThreadPoolUtils();
    }


}
