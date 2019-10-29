package demo.netty.pipeline.channel;

import demo.netty.pipeline.util.DefaultAttributeMap;
import java.util.UUID;

/**
 * {@link Channel} 的一个抽象实现
 *
 * @author leishiguang
 * @since v1.0
 */
public abstract class AbstractChannel extends DefaultAttributeMap implements Channel {

  protected final String id;
  protected final DefaultChannelPipeline pipeline;


  protected AbstractChannel() {
    id = newId();
    pipeline = newChannelPipeline();
  }

  /**
   * 生成通道职责链
   */
  protected DefaultChannelPipeline newChannelPipeline() {
    return new DefaultChannelPipeline(this);
  }

  /**
   * 生成通道ID
   */
  protected String newId() {
    return UUID.randomUUID().toString();
  }

  /**
   * 返回当前通道的 id
   *
   * @return String 通道id
   */
  @Override
  public String id() {
    return id;
  }


  /**
   * 获取当前通道的 pipeline
   *
   * @return ChannelPipeline 通道pipeline
   */
  @Override
  public ChannelPipeline pipeline() {
    return pipeline;
  }


}
