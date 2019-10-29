package demo.netty.pipeline;

import demo.netty.pipeline.channel.AbstractChannelInitializer;
import demo.netty.pipeline.channel.Channel;
import demo.netty.pipeline.channel.ChannelOption;
import demo.netty.pipeline.channel.ChannelPipeline;
import demo.netty.pipeline.channel.DefaultChannel;
import demo.netty.pipeline.channel.SimpleBootstrap;
import demo.netty.pipeline.util.AttributeKey;
import java.util.HashMap;
import java.util.Map;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class ChannelTest {

  public static void main(String[] args) {
    test2();
  }

  public static void test1() {
    DefaultChannel defaultChannel = new DefaultChannel();
    //defaultChannel.pipeline().addFirst("日志处理", new LogEncryptedHandler());
    defaultChannel.attr(AttributeKey.valueOf("userId")).getAndSet("这是测试的");
    defaultChannel.receivedOutside();
    defaultChannel.sendOut();
    Map<String, String> testMap = new HashMap<>();
    testMap.put("testMapKey", "testMapValue");
    defaultChannel.attr(AttributeKey.newInstance("xyzz")).set(testMap);
    System.out.println("done" + defaultChannel.attr(AttributeKey.valueOf("userId")).get());
  }

  public static void test2() {
    //构造当前查询的通道
    SimpleBootstrap bootstrap = new SimpleBootstrap();
    bootstrap.channel(DefaultChannel.class)
        .option(ChannelOption.CHANNEL_ID, "通道名称")
        .option(ChannelOption.CHANNEL_NAME, "服务ID")
        //.attr(MianyangChannelAttributeKey.DEFAULT_KEY, "默认参数1")
        //.attr(MianyangChannelAttributeKey.CHANNEL_RESULT, "默认结果1")
        .handler(new AbstractChannelInitializer() {
          @Override
          protected void initChannel(Channel ch) throws Exception {
            ChannelPipeline cp = ch.pipeline();
            //添加请求格式转换的职责链
            //cp.addLast("请求格式转换", new LogEncryptedHandler());
            //添加执行请求的职责链
            //cp.addLast("执行请求", new LogEncryptedHandler());
            //添加返回格式转换职责链；
            //cp.addLast("返回格式转换", new LogEncryptedHandler());
          }
        });
    //执行初始化
    DefaultChannel channel = (DefaultChannel) bootstrap.start();
    //执行入站处理
    channel.receivedOutside();
    //获取执行后的结果
    //Object object = channel.result();
  }
}
