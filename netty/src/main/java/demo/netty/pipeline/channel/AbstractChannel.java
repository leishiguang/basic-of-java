package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.DefaultAttributeMap;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class AbstractChannel extends DefaultAttributeMap implements Channel {

  private final String id;
  private final DefaultChannelPipeline pipeline;
  private final ChannelConfig config;

  protected AbstractChannel() {
    id = "AbstractChannel通道id";
    pipeline = newChannelPipeline();
    config = newChannelConfig();
  }

  /**
   * 生成通道 id
   */
  protected DefaultChannelPipeline newChannelPipeline() {
    return new DefaultChannelPipeline(this);
  }

  protected DefaultChannelConfig newChannelConfig() {
    return new DefaultChannelConfig(this);
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
   * 返回当前通道的配置信息
   *
   * @return ChannelConfig 通道配置
   */
  @Override
  public ChannelConfig config() {
    return config;
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

  /**
   * 执行通道的事件
   *
   * @return
   */
  @Override
  public Channel invokeInbound() {
    pipeline.beforeAll();
    pipeline.beforeAssemble();
    pipeline.assemble();
    pipeline.afterAssemble();
    pipeline.beforeRequest();
    pipeline.request();
    pipeline.afterRequest();
    pipeline.beforeRemould();
    pipeline.remould();
    pipeline.afterRemould();
    pipeline.afterAll();
    return this;
  }

  @Override
  public Channel invokeOutbound() {
    pipeline.beforeAllOutBound();
    pipeline.beforeAssembleOutBound();
    pipeline.assembleOutBound();
    pipeline.afterAssembleOutBound();
    pipeline.beforeRequestOutBound();
    pipeline.requestOutBound();
    pipeline.afterRequestOutBound();
    pipeline.beforeRemouldOutBound();
    pipeline.remouldOutBound();
    pipeline.afterRemouldOutBound();
    pipeline.afterAllOutBound();
    return this;
  }
}
