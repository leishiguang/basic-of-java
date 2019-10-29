package demo.netty.pipeline.channel;

import demo.netty.pipeline.util.internal.ObjectUtil;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/**
 * 默认的职责链
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelPipeline implements ChannelPipeline {

  private final AbstractChannelHandlerContext head;
  private final AbstractChannelHandlerContext tail;
  private final Channel channel;

  DefaultChannelPipeline(Channel channel) {
    this.channel = ObjectUtil.checkNotNull(channel, "channel");
    //succeededFuture = new SucceededChannelFuture(channel, null);
    //voidPromise =  new VoidChannelPromise(channel, true);

    tail = new TailContext(this);
    head = new HeadContext(this);

    head.next = tail;
    tail.prev = head;
  }

  /**
   * 在职责链的开头，添加一个新的处理类
   *
   * @param name    处理器名称
   * @param handler 处理器
   * @return ChannelPipeline
   */
  @Override
  public ChannelPipeline addFirst(String name, ChannelHandler handler) {
    final AbstractChannelHandlerContext newCtx;
    synchronized (this) {
      name = filterName(name, handler);
      newCtx = newContext(name, handler);
      addFirst0(newCtx);
    }
    return this;
  }

  /**
   * Inserts {@link ChannelHandler}s at the first position of this pipeline.
   *
   * @param handlers the handlers to insert first
   */
  @Override
  public ChannelPipeline addFirst(ChannelHandler... handlers) {
    int size = checkChannelHandlers(handlers);
    for (int i = size - 1; i >= 0; i--) {
      ChannelHandler h = handlers[i];
      addFirst(null, h);
    }
    return this;
  }

  /**
   * 在职责链的末尾，添加一个新的处理类
   *
   * @param name    处理器名称
   * @param handler 处理器
   * @return ChannelPipeline
   */
  @Override
  public ChannelPipeline addLast(String name, ChannelHandler handler) {
    final AbstractChannelHandlerContext newCtx;
    synchronized (this) {
      name = filterName(name, handler);
      newCtx = newContext(name, handler);
      addLast0(newCtx);
    }
    return this;
  }

  /**
   * Inserts {@link ChannelHandler}s at the last position of this pipeline.
   *
   * @param handlers the handlers to insert last
   */
  @Override
  public ChannelPipeline addLast(ChannelHandler... handlers) {
    int size = checkChannelHandlers(handlers);
    for (int i = size - 1; i >= 0; i--) {
      ChannelHandler h = handlers[i];
      addLast(null, h);
    }
    return this;
  }


  @Override
  public final ChannelHandlerContext context(ChannelHandler handler) {
    if (handler == null) {
      throw new NullPointerException("handler");
    }

    AbstractChannelHandlerContext ctx = head.next;
    for (; ; ) {

      if (ctx == null) {
        return null;
      }

      if (ctx.handler() == handler) {
        return ctx;
      }

      ctx = ctx.next;
    }
  }

  @Override
  public final ChannelPipeline remove(ChannelHandler handler) {
    remove(getContextOrDie(handler));
    return this;
  }

  private AbstractChannelHandlerContext remove(final AbstractChannelHandlerContext ctx) {
    assert ctx != head && ctx != tail;
    synchronized (this) {
      remove0(ctx);
      return ctx;
    }
  }

  private static void remove0(AbstractChannelHandlerContext ctx) {
    AbstractChannelHandlerContext prev = ctx.prev;
    AbstractChannelHandlerContext next = ctx.next;
    prev.next = next;
    next.prev = prev;
  }

  private AbstractChannelHandlerContext getContextOrDie(ChannelHandler handler) {
    AbstractChannelHandlerContext ctx = (AbstractChannelHandlerContext) context(handler);
    if (ctx == null) {
      throw new NoSuchElementException(handler.getClass().getName());
    } else {
      return ctx;
    }
  }

  private int checkChannelHandlers(ChannelHandler... handlers) {
    if (handlers == null) {
      throw new NullPointerException("handlers");
    }
    if (handlers.length == 0 || handlers[0] == null) {
      return 0;
    }
    int size;
    for (size = 1; size < handlers.length; size++) {
      if (handlers[size] == null) {
        break;
      }
    }
    return size;
  }

  /**
   * 将职责链转化为有序的 map，key为名称，value 为 handler
   *
   * @return Map<String, ChannelHandler>
   */
  @Override
  public Map<String, ChannelHandler> toMap() {
    Map<String, ChannelHandler> map = new LinkedHashMap<>();
    AbstractChannelHandlerContext ctx = head.next;
    for (; ; ) {
      if (ctx == tail) {
        return map;
      }
      map.put(ctx.name(), ctx.handler());
      ctx = ctx.next;
    }
  }

  /**
   * Returns the {@link Channel} that this pipeline is attached to.
   *
   * @return the channel. {@code null} if this pipeline is not attached yet.
   */
  @Override
  public Channel channel() {
    return channel;
  }

  @Override
  public ChannelInboundInvoker initalizer() {
    AbstractChannelHandlerContext.invokeChannelInitalizer(head);
    return this;
  }

  /**
   * 入站的数据准备
   */
  @Override
  public ChannelPipeline beforeAll() {
    AbstractChannelHandlerContext.invokeChannelBeforeAll(head);
    return this;
  }

  /**
   * 组装
   */
  @Override
  public ChannelPipeline beforeAssemble() {
    AbstractChannelHandlerContext.invokeChannelBeforeAssemble(head);
    return this;
  }

  /**
   * 组装
   */
  @Override
  public ChannelPipeline assemble() {
    AbstractChannelHandlerContext.invokeChannelAssemble(head);
    return this;
  }

  /**
   * 组装之后
   */
  @Override
  public ChannelPipeline afterAssemble() {
    AbstractChannelHandlerContext.invokeChannelAfterAssemble(head);
    return this;
  }

  /**
   * 请求之前
   */
  @Override
  public ChannelPipeline beforeRequest() {
    AbstractChannelHandlerContext.invokeChannelBeforeRequest(head);
    return this;
  }

  /**
   * 执行入站请求
   */
  @Override
  public ChannelPipeline request() {
    AbstractChannelHandlerContext.invokeChannelRequest(head);
    return this;
  }

  /**
   * 请求之后
   */
  @Override
  public ChannelPipeline afterRequest() {
    AbstractChannelHandlerContext.invokeChannelAfterRequest(head);
    return this;
  }

  /**
   * 改造响应数据之前
   */
  @Override
  public ChannelPipeline beforeRemould() {
    AbstractChannelHandlerContext.invokeChannelBeforeRemould(head);
    return this;
  }

  /**
   * 改造响应数据
   */
  @Override
  public ChannelPipeline remould() {
    AbstractChannelHandlerContext.invokeChannelRemould(head);
    return this;
  }

  /**
   * 改造相应数据之后
   */
  @Override
  public ChannelPipeline afterRemould() {
    AbstractChannelHandlerContext.invokeChannelAfterRemould(head);
    return this;
  }

  /**
   * 入站请求之后
   */
  @Override
  public ChannelPipeline afterAll() {
    AbstractChannelHandlerContext.invokeChannelAfterAll(head);
    return this;
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<Entry<String, ChannelHandler>> iterator() {
    return toMap().entrySet().iterator();
  }


  /**
   * 出站前的请求编码
   */
  @Override
  public ChannelOutboundInvoker beforeAllOutBound() {
    tail.beforeAllOutBound();
    return this;
  }

  /**
   * 组装
   */
  @Override
  public ChannelOutboundInvoker beforeAssembleOutBound() {
    tail.beforeAssembleOutBound();
    return this;
  }

  /**
   * 组装
   */
  @Override
  public ChannelOutboundInvoker assembleOutBound() {
    tail.assembleOutBound();
    return this;
  }

  /**
   * 组装之后
   */
  @Override
  public ChannelOutboundInvoker afterAssembleOutBound() {
    tail.afterAssembleOutBound();
    return this;
  }

  /**
   * 请求之前
   */
  @Override
  public ChannelOutboundInvoker beforeRequestOutBound() {
    tail.beforeRequestOutBound();
    return this;
  }

  /**
   * 执行请求
   */
  @Override
  public ChannelOutboundInvoker requestOutBound() {
    tail.requestOutBound();
    return this;
  }

  /**
   * 请求之后
   */
  @Override
  public ChannelOutboundInvoker afterRequestOutBound() {
    tail.afterRequestOutBound();
    return this;
  }

  /**
   * 改造响应数据之前
   */
  @Override
  public ChannelOutboundInvoker beforeRemouldOutBound() {
    tail.beforeRemouldOutBound();
    return this;
  }

  /**
   * 改造响应数据
   */
  @Override
  public ChannelOutboundInvoker remouldOutBound() {
    tail.remouldOutBound();
    return this;
  }

  /**
   * 改造相应数据之后
   */
  @Override
  public ChannelOutboundInvoker afterRemouldOutBound() {
    tail.afterRemouldOutBound();
    return this;
  }

  /**
   * 出站站请求之后
   */
  @Override
  public ChannelOutboundInvoker afterAllOutBound() {
    tail.afterAllOutBound();
    return this;
  }

  private String filterName(String name, ChannelHandler handler) {
    if (name == null) {
      return generateName(handler);
    }
    return name;
  }

  private String generateName(ChannelHandler handler) {
    return handler.getClass().getSimpleName();
  }

  private AbstractChannelHandlerContext newContext(String name, ChannelHandler handler) {
    return new DefaultChannelHandlerContext(this, name, handler);
  }

  private void addFirst0(AbstractChannelHandlerContext newCtx) {
    AbstractChannelHandlerContext nextCtx = head.next;
    newCtx.prev = head;
    newCtx.next = nextCtx;
    head.next = newCtx;
    nextCtx.prev = newCtx;
  }

  private void addLast0(AbstractChannelHandlerContext newCtx) {
    AbstractChannelHandlerContext prev = tail.prev;
    newCtx.prev = prev;
    newCtx.next = tail;
    prev.next = newCtx;
    tail.prev = newCtx;
  }


  static final class HeadContext extends AbstractChannelHandlerContext
      implements ChannelInboundHandler, ChannelOutboundHandler {

    HeadContext(DefaultChannelPipeline pipeline) {
      super("HEAD_NAME", true, true, pipeline);
    }

    @Override
    public ChannelHandler handler() {
      return this;
    }

    @Override
    public void channelInitalizer(ChannelHandlerContext ctx) {
      ctx.initalizer();
    }

    @Override
    public void channelBeforeAll(ChannelHandlerContext ctx) {
      ctx.beforeAll();
    }

    @Override
    public void channelBeforeAssemble(ChannelHandlerContext ctx) {
      if (ctx != null) {
        ctx.beforeAssemble();
      }
    }

    @Override
    public void channelAssemble(ChannelHandlerContext ctx) {
      ctx.assemble();
    }

    @Override
    public void channelAfterAssemble(ChannelHandlerContext ctx) {
      ctx.afterAssemble();
    }

    @Override
    public void channelBeforeRequest(ChannelHandlerContext ctx) {
      ctx.beforeRequest();
    }

    @Override
    public void channelRequest(ChannelHandlerContext ctx) {
      ctx.request();
    }

    @Override
    public void channelAfterRequest(ChannelHandlerContext ctx) {
      ctx.afterRequest();
    }

    @Override
    public void channelBeforeRemould(ChannelHandlerContext ctx) {
      ctx.beforeRemould();
    }

    @Override
    public void channelRemould(ChannelHandlerContext ctx) {
      ctx.remould();
    }

    @Override
    public void channelAfterRemould(ChannelHandlerContext ctx) {
      ctx.afterRemould();
    }

    @Override
    public void channelAfterAll(ChannelHandlerContext ctx) {
      ctx.afterAll();
    }

    @Override
    public void channelBeforeAllOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeAssembleOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAssembleOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterAssembleOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeRequestOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelRequestOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterRequestOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeRemouldOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelRemouldOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterRemouldOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterAllOutBound(ChannelHandlerContext ctx) {
      //No need to deal with
    }
  }

  static final class TailContext extends AbstractChannelHandlerContext implements
      ChannelInboundHandler {

    TailContext(DefaultChannelPipeline pipeline) {
      super("TAIL_NAME", true, false, pipeline);
    }

    @Override
    public ChannelHandler handler() {
      return this;
    }

    @Override
    public void channelInitalizer(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeAll(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeAssemble(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAssemble(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterAssemble(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeRequest(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelRequest(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterRequest(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelBeforeRemould(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelRemould(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterRemould(ChannelHandlerContext ctx) {
      //No need to deal with
    }

    @Override
    public void channelAfterAll(ChannelHandlerContext ctx) {
      //No need to deal with
    }

  }

}
