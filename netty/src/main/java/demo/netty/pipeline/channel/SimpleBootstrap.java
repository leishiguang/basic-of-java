package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.AttributeKey;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 通道的初始化，默认实现
 *
 * @author leishiguang
 * @since v1.0
 */
public class SimpleBootstrap extends AbstractBootstrap<SimpleBootstrap, Channel> {

  @Override
  @SuppressWarnings("unchecked")
  void init(Channel channel) throws Exception {
    ChannelPipeline p = channel.pipeline();
    final ChannelHandler handler = handler0();
    synchronized (handler) {
      p.addLast(handler);
    }
    final Map<ChannelOption<?>, Object> options = options0();
    synchronized (options) {
      setChannelOptions(channel, options);
    }

    final Map<AttributeKey<?>, Object> attrs = attrs0();
    synchronized (attrs) {
      for (Entry<AttributeKey<?>, Object> e : attrs.entrySet()) {
        channel.attr((AttributeKey<Object>) e.getKey()).set(e.getValue());
      }
    }

  }

  /**
   * todo 执行通道的校验，比如：必须设置的通道配置、参数
   */
  @Override
  void verify(Channel channel) throws Exception {

  }
}
