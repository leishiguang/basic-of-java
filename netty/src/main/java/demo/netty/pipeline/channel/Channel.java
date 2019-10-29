package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.AttributeMap;

/**
 * 通道的顶层定义，表示一次请求事件的传递。
 *
 * 通道具有的属性：
 *
 * 通道的作用：
 *
 * 入站请求的示例：
 * <pre>
 *   Channel channel = new DefaultChannel();
 *   channel.invokeInbound();
 *   Object result = channelhannel.attr(AttributeKey.valueOf("result")).get();
 * </pre>
 *
 * 出站请求的示例：
 * <pre>
 *   Channel channel = new DefaultChannel();
 *   channel.invokeInbound();
 *   return channel.attr.
 * </pre>
 *
 * @author leishiguang
 * @since v1.0
 */
public interface Channel extends AttributeMap {


  /**
   * 返回当前通道的 id
   *
   * @return String 通道id
   */
  String id();

  /**
   * 返回当前通道的配置信息
   *
   * @return ChannelConfig 通道配置
   */
  ChannelConfig config();

  /**
   * 获取当前通道的 pipeline
   *
   * @return ChannelPipeline 通道pipeline
   */
  ChannelPipeline pipeline();

  /**
   * 接收到了外部数据，比如接收到第三方的查询请求
   */
  Channel receivedOutside();

  /**
   * 要通知外部，比如将进度数据发送给外部
   */
  Channel sendOut();

}
