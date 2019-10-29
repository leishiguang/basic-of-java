package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.DefaultAttributeMap;
import org.omg.SendingContext.RunTime;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public abstract class AbstractChannelHandlerContext extends DefaultAttributeMap implements
    ChannelHandlerContext {

  volatile AbstractChannelHandlerContext next;
  volatile AbstractChannelHandlerContext prev;
  private final String name;
  private final boolean inbound;
  private final boolean outbound;
  private final DefaultChannelPipeline pipeline;

  public AbstractChannelHandlerContext(String name, boolean inbound, boolean outbound,
      DefaultChannelPipeline pipeline) {
    this.name = name;
    this.inbound = inbound;
    this.outbound = outbound;
    this.pipeline = pipeline;
  }

  /**
   * 当前职责链的名称
   *
   * @return 名称
   */
  @Override
  public String name() {
    return name;
  }

  /**
   * 返回当前通道
   */
  @Override
  public Channel channel() {
    return pipeline.channel();
  }

  /**
   * Return the assigned {@link ChannelPipeline}
   */
  @Override
  public ChannelPipeline pipeline() {
    return pipeline;
  }

  @Override
  public ChannelInboundInvoker initalizer() {
    invokeChannelInitalizer(findContextInbound());
    return this;
  }

  static void invokeChannelInitalizer(final AbstractChannelHandlerContext next) {
    next.invokeChannelInitalizer();
  }

  private void invokeChannelInitalizer() {
    try {
      ((ChannelInboundHandler) handler()).channelInitalizer(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  @Override
  public ChannelHandlerContext beforeAll() {
    invokeChannelBeforeAll(findContextInbound());
    return this;
  }

  static void invokeChannelBeforeAll(final AbstractChannelHandlerContext next) {
    next.invokeChannelBeforeAll();
  }

  private void invokeChannelBeforeAll() {
    try {
      ((ChannelInboundHandler) handler()).channelBeforeAll(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 组装
   */
  @Override
  public ChannelHandlerContext beforeAssemble() {
    invokeChannelBeforeAssemble(findContextInbound());
    return this;
  }

  static void invokeChannelBeforeAssemble(final AbstractChannelHandlerContext next) {
    next.invokeChannelBeforeAssemble();
  }

  private void invokeChannelBeforeAssemble() {
    try {
      ((ChannelInboundHandler) handler()).channelBeforeAssemble(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 组装
   */
  @Override
  public ChannelHandlerContext assemble() {
    invokeChannelAssemble(findContextInbound());
    return this;
  }

  static void invokeChannelAssemble(final AbstractChannelHandlerContext next) {
    next.invokeChannelAssemble();
  }

  private void invokeChannelAssemble() {
    try {
      ((ChannelInboundHandler) handler()).channelAssemble(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }


  /**
   * 组装之后
   */
  @Override
  public ChannelHandlerContext afterAssemble() {
    invokeChannelAfterAssemble(findContextInbound());
    return this;
  }

  static void invokeChannelAfterAssemble(final AbstractChannelHandlerContext next) {
    next.invokeChannelAfterAssemble();
  }

  private void invokeChannelAfterAssemble() {
    try {
      ((ChannelInboundHandler) handler()).channelAfterAssemble(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }


  /**
   * 请求之前
   */
  @Override
  public ChannelHandlerContext beforeRequest() {
    invokeChannelBeforeRequest(findContextInbound());
    return this;
  }

  static void invokeChannelBeforeRequest(final AbstractChannelHandlerContext next) {
    next.invokeChannelBeforeRequest();
  }

  private void invokeChannelBeforeRequest() {
    try {
      ((ChannelInboundHandler) handler()).channelBeforeRequest(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  @Override
  public ChannelHandlerContext request() {
    invokeChannelRequest(findContextInbound());
    return this;
  }

  static void invokeChannelRequest(final AbstractChannelHandlerContext next) {
    next.invokeChannelRequest();
  }

  private void invokeChannelRequest() {
    try {
      ((ChannelInboundHandler) handler()).channelRequest(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 请求之后
   */
  @Override
  public ChannelHandlerContext afterRequest() {
    invokeChannelAfterRequest(findContextInbound());
    return this;
  }

  static void invokeChannelAfterRequest(final AbstractChannelHandlerContext next) {
    next.invokeChannelAfterRequest();
  }

  private void invokeChannelAfterRequest() {
    try {
      ((ChannelInboundHandler) handler()).channelAfterRequest(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 改造响应数据之前
   */
  @Override
  public ChannelHandlerContext beforeRemould() {
    invokeChannelBeforeRemould(findContextInbound());
    return this;
  }

  static void invokeChannelBeforeRemould(final AbstractChannelHandlerContext next) {
    next.invokeChannelBeforeRemould();
  }

  private void invokeChannelBeforeRemould() {
    try {
      ((ChannelInboundHandler) handler()).channelBeforeRemould(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 改造响应数据
   */
  @Override
  public ChannelHandlerContext remould() {
    invokeChannelRemould(findContextInbound());
    return this;
  }

  static void invokeChannelRemould(final AbstractChannelHandlerContext next) {
    next.invokeChannelRemould();
  }

  private void invokeChannelRemould() {
    try {
      ((ChannelInboundHandler) handler()).channelRemould(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 改造相应数据之后
   */
  @Override
  public ChannelHandlerContext afterRemould() {
    invokeChannelAfterRemould(findContextInbound());
    return this;
  }

  static void invokeChannelAfterRemould(final AbstractChannelHandlerContext next) {
    next.invokeChannelAfterRemould();
  }

  private void invokeChannelAfterRemould() {
    try {
      ((ChannelInboundHandler) handler()).channelAfterRemould(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 入站请求之后
   */
  @Override
  public ChannelHandlerContext afterAll() {
    invokeChannelAfterAll(findContextInbound());
    return this;
  }

  static void invokeChannelAfterAll(final AbstractChannelHandlerContext next) {
    next.invokeChannelAfterAll();
  }

  private void invokeChannelAfterAll() {
    try {
      ((ChannelInboundHandler) handler()).channelAfterAll(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }


  @Override
  public ChannelHandlerContext beforeAllOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeBeforeAllOutBound();
    return this;
  }

  private void invokeBeforeAllOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelBeforeAllOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }


  /**
   * 组装
   */
  @Override
  public ChannelHandlerContext beforeAssembleOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeBeforeAssembleOutBound();
    return this;
  }

  private void invokeBeforeAssembleOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelBeforeAssembleOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 组装
   */
  @Override
  public ChannelHandlerContext assembleOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeAssembleOutBound();
    return this;
  }

  private void invokeAssembleOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelAssembleOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 组装之后
   */
  @Override
  public ChannelHandlerContext afterAssembleOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeAfterAssembleOutBound();
    return this;
  }

  private void invokeAfterAssembleOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelAfterAssembleOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 请求之前
   */
  @Override
  public ChannelHandlerContext beforeRequestOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeBeforeRequestOutBound();
    return this;
  }

  private void invokeBeforeRequestOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelBeforeRequestOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 执行请求
   */
  @Override
  public ChannelHandlerContext requestOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeRequestOutBound();
    return this;
  }

  private void invokeRequestOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelRequestOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 请求之后
   */
  @Override
  public ChannelHandlerContext afterRequestOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeAfterRequestOutBound();
    return this;
  }

  private void invokeAfterRequestOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelAfterRequestOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }


  /**
   * 改造响应数据之前
   */
  @Override
  public ChannelHandlerContext beforeRemouldOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeBeforeRemouldOutBound();
    return this;
  }

  private void invokeBeforeRemouldOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelBeforeRemouldOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 改造响应数据
   */
  @Override
  public ChannelHandlerContext remouldOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeRemouldOutBound();
    return this;
  }

  private void invokeRemouldOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelRemouldOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 改造相应数据之后
   */
  @Override
  public ChannelHandlerContext afterRemouldOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeAfterRemouldOutBound();
    return this;
  }

  private void invokeAfterRemouldOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelAfterRemouldOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  /**
   * 请求之后
   */
  @Override
  public ChannelHandlerContext afterAllOutBound() {
    final AbstractChannelHandlerContext next = findContextOutbound();
    next.invokeAfterAllOutBound();
    return this;
  }

  private void invokeAfterAllOutBound() {
    try {
      ((ChannelOutboundHandler) handler()).channelAfterAllOutBound(this);
    } catch (Throwable t) {
      notifyHandlerException(t);
    }
  }

  private AbstractChannelHandlerContext findContextInbound() {
    AbstractChannelHandlerContext ctx = this;
    do {
      ctx = ctx.next;
    } while (!ctx.inbound);
    return ctx;
  }

  private AbstractChannelHandlerContext findContextOutbound() {
    AbstractChannelHandlerContext ctx = this;
    do {
      ctx = ctx.prev;
    } while (!ctx.outbound);
    return ctx;
  }


  private void notifyHandlerException(Throwable t) {
    throw new RuntimeException(t.getMessage(), t);
  }
}
