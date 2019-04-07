package com.coderay.netty.websocket.test;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.coderay.netty.websocket.handler.WebSocketServerHandler;

// 正常情况是，后台系统通过接口请求，把数据丢到对应的MQ队列，再由推送服务器读取
public class TestCenter {
    // 此处假设一个用户一台设备，否则用户的通道应该是多个。
    // todo 还应该有一个定时任务，用于检测失效的连接(类似缓存中的LRU算法，长时间不使用，就拿出来检测一下是否断开了)；
    private static ConcurrentHashMap<String, Channel> userInfos = new ConcurrentHashMap<>();

    // 保存信息
    public static void saveConnection(String userId, Channel channel) {
        userInfos.put(userId, channel);
    }

    // 退出的时候移除掉
    public static void removeConnection(Object userId) {
        if (userId != null) {
            userInfos.remove(userId.toString());
        }
    }

    private final static byte[] JUST_TEST = new byte[1024];

    public static void startTest() {
        // 发一个tony吧
        System.arraycopy("tony".getBytes(), 0, JUST_TEST, 0, 4);
        //设置是否开启自动推送
        final String sendmsg = System.getProperty("netease.server.test.sendmsg", "false");
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                // 压力测试，在用户中随机抽取1/10进行发送
                if (userInfos.isEmpty()) {
                    return;
                }
                int size = userInfos.size();
                ConcurrentHashMap.KeySetView<String, Channel> keySetView = userInfos.keySet();
                String[] keys = keySetView.toArray(new String[]{});
                System.out.println(WebSocketServerHandler.counter.sum() + " : 当前用户数量" + keys.length);
                if (Boolean.valueOf(sendmsg)) { // 是否开启发送
                    for (int i = 0; i < (size > 10 ? size / 10 : size); i++) {
                        // 提交任务给它执行
                        String key = keys[new Random().nextInt(size)];
                        Channel channel = userInfos.get(key);
                        if (channel == null) {
                            continue;
                        }
                        if (!channel.isActive()) {
                            userInfos.remove(key);
                            continue;
                        }
                        channel.eventLoop().execute(() -> {
                            channel.writeAndFlush(new TextWebSocketFrame(new String(JUST_TEST))); // 推送1024字节
                        });

                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, 1000L, 2000L, TimeUnit.MILLISECONDS);
    }
}
