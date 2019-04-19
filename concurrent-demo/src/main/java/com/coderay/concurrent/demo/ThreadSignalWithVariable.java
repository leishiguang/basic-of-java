package com.coderay.concurrent.demo;

/**
 * 使用共享变量进行线程通信
 * date 2019/04/18
 * @author leishiguang
 */
class ThreadSignalWithVariable {

    /**
     * 共享变量
     */
    private static String content = "空";

    public static void main(String[] args) {
        // 线程1 - 写入数据
        new Thread(() -> {
            try {
                while (true) {
                    content = "当前时间：" + System.currentTimeMillis();
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 线程2 - 读取数据
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    System.out.println(content);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
