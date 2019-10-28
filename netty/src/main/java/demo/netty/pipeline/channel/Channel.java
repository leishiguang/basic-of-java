package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.AttributeMap;

/**
 * 通道的顶层定义
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
   * 执行通道的入站职责链
   *
   * @return
   */
  Channel invokeInbound();

  /**
   * 执行通道的出站职责链
   *
   * @return
   */
  Channel invokeOutbound();
}
