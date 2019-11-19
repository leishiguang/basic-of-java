package demo.netty.pipeline.channel;


import demo.netty.pipeline.exception.ChannelException;

/**
 * 默认的 Channel
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannel extends AbstractChannel {

  private final DefaultChannelConfig config;
  protected boolean active;
  protected Throwable throwable;

  public DefaultChannel() {
    this.config = new DefaultChannelConfig(this);
    this.active = true;
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

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public void stop() {
    this.active = false;
  }

  @Override
  public Throwable throwable() {
    return throwable;
  }

  @Override
  public void throwable(Throwable throwable) {
    this.throwable = throwable;
  }

  /**
   * 执行通道的入站事件
   */
  public Channel receivedOutside() {
    if (active) {
      pipeline.beforeAll();
    }
    if (active) {
      pipeline.beforeAssemble();
    }
    if (active) {
      pipeline.assemble();
    }
    if (active) {
      pipeline.afterAssemble();
    }
    if (active) {
      pipeline.beforeRequest();
    }
    if (active) {
      pipeline.request();
    }
    if (active) {
      pipeline.afterRequestOutBound();
    }
    if (active) {
      pipeline.beforeRemouldOutBound();
    }
    if (active) {
      pipeline.remouldOutBound();
    }
    if (active) {
      pipeline.afterRemouldOutBound();
    }
    if (active) {
      pipeline.afterAllOutBound();
    }
    if (!active && throwable != null) {
      throw new ChannelException(throwable);
    }
    return this;
  }

  /**
   * 执行通道的出站事件
   */
  public Channel sendOut() {
    if (active) {
      pipeline.beforeAllOutBound();
    }
    if (active) {
      pipeline.beforeAssembleOutBound();
    }
    if (active) {
      pipeline.assembleOutBound();
    }
    if (active) {
      pipeline.afterAssembleOutBound();
    }
    if (active) {
      pipeline.beforeRequestOutBound();
    }
    if (active) {
      pipeline.requestOutBound();
    }
    if (active) {
      pipeline.afterRequest();
    }
    if (active) {
      pipeline.beforeRemould();
    }
    if (active) {
      pipeline.remould();
    }
    if (active) {
      pipeline.afterRemould();
    }
    if (active) {
      pipeline.afterAll();
    }
    if (!active && throwable != null) {
      throw new ChannelException(throwable);
    }
    return this;
  }

}
