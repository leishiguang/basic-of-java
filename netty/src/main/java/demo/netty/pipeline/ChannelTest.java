package demo.netty.pipeline;

import demo.netty.pipeline.channel.DefaultChannel;
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
    DefaultChannel defaultChannel = new DefaultChannel();

    //defaultChannel.pipeline().addFirst("绵阳加密",);

    defaultChannel.attr(AttributeKey.valueOf("userId")).getAndSet("这是测试的");
    defaultChannel.invokeInbound();
    defaultChannel.invokeOutbound();
    Map<String, String> testMap = new HashMap<>();
    testMap.put("testMapKey", "testMapValue");
    defaultChannel.attr(AttributeKey.newInstance("xyzz")).set(testMap);
    System.out.println("done" + defaultChannel.attr(AttributeKey.valueOf("userId")).get());
  }
}
