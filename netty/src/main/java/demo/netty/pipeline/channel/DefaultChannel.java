package demo.netty.pipeline.channel;

/**
 * 默认的 Channel
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannel extends AbstractChannel {

  private final DefaultChannelConfig config;

  public DefaultChannel() {
    this.config = new DefaultChannelConfig(this);
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
   * 执行通道的入站事件
   */
  @Override
  public Channel receivedOutside() {
    pipeline.beforeAll();
    pipeline.beforeAssemble();
    pipeline.assemble();
    pipeline.afterAssemble();
    pipeline.beforeRequest();
    pipeline.request();
    pipeline.afterRequestOutBound();
    pipeline.beforeRemouldOutBound();
    pipeline.remouldOutBound();
    pipeline.afterRemouldOutBound();
    pipeline.afterAllOutBound();
    return this;
  }

  /**
   * 执行通道的出站事件
   */
  @Override
  public Channel sendOut() {
    pipeline.beforeAllOutBound();
    pipeline.beforeAssembleOutBound();
    pipeline.assembleOutBound();
    pipeline.afterAssembleOutBound();
    pipeline.beforeRequestOutBound();
    pipeline.requestOutBound();
    pipeline.afterRequest();
    pipeline.beforeRemould();
    pipeline.remould();
    pipeline.afterRemould();
    pipeline.afterAll();
    return this;
  }

}
