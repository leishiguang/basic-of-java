package demo.concurrent.thread;

import demo.common.ThreadPoolUtils;

/**
 * 使用共享变量进行线程通信
 *
 * @author leishiguang
 * @version 1.0
 */
class ThreadSignalWithVariableTest {

    /**
     * 共享变量
     */
    private static String content = "空";

    public static void main(String[] args) {
        long breakTimeMillis = System.currentTimeMillis() + 10000;

        //线程1 - 写入数据
        ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
            try {
                while (System.currentTimeMillis() < breakTimeMillis) {
                    content = "当前时间：" + System.currentTimeMillis();
                    Thread.sleep(500L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 线程2 - 读取数据
        ThreadPoolUtils.getInstance().getExecutorService().execute(() -> {
            try {
                while (System.currentTimeMillis() < breakTimeMillis) {
                    Thread.sleep(500L);
                    System.out.println(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
