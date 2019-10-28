package demo.netty.pipeline.channel;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 默认的职责链
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelPipeline implements ChannelPipeline {

  final AbstractChannelHandlerContext head;
  final AbstractChannelHandlerContext tail;

  protected DefaultChannelPipeline(Channel channel) {
    //this.channel = ObjectUtil.checkNotNull(channel, "channel");
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
   * 将职责链转化为有序的 map，key为名称，value 为 handler
   *
   * @return Map<String, ChannelHandler>
   */
  @Override
  public Map<String, ChannelHandler> toMap() {
    Map<String, ChannelHandler> map = new LinkedHashMap<String, ChannelHandler>();
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


  final class HeadContext extends AbstractChannelHandlerContext
      implements ChannelInboundHandler, ChannelOutboundHandler {

    HeadContext(DefaultChannelPipeline pipeline) {
      super("HEAD_NAME", true, true, pipeline);
    }

    @Override
    public ChannelHandler handler() {
      return this;
    }

    @Override
    public void channelBeforeAll(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeAll()");
      ctx.beforeAll();
    }

    @Override
    public void channelBeforeAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeAssemble()");
      if (ctx != null) {
        ctx.beforeAssemble();
      }
    }

    @Override
    public void channelAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAssemble()");
      ctx.assemble();
    }

    @Override
    public void channelAfterAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterAssemble()");
      ctx.afterAssemble();
    }

    @Override
    public void channelBeforeRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeRequest()");
      ctx.beforeRequest();
    }

    @Override
    public void channelRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelRequest()");
      ctx.request();
    }

    @Override
    public void channelAfterRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterRequest()");
      ctx.afterRequest();
    }

    @Override
    public void channelBeforeRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeRemould()");
      ctx.beforeRemould();
    }

    @Override
    public void channelRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelRemould()");
      ctx.remould();
    }

    @Override
    public void channelAfterRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterRemould()");
      ctx.afterRemould();
    }

    @Override
    public void channelAfterAll(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterAll()");
      ctx.afterAll();
    }

    @Override
    public void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeAllOutBound()");
    }

    @Override
    public void channelBeforeAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeAssembleOutBound()");

    }

    @Override
    public void channelAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAssembleOutBound()");

    }

    @Override
    public void channelAfterAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterAssembleOutBound()");

    }

    @Override
    public void channelBeforeRequestOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeRequestOutBound()");

    }

    @Override
    public void channelRequestOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelRequestOutBound()");

    }

    @Override
    public void channelAfterRequestOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterRequestOutBound()");

    }

    @Override
    public void channelBeforeRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelBeforeRemouldOutBound()");

    }

    @Override
    public void channelRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelRemouldOutBound()");

    }

    @Override
    public void channelAfterRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterRemouldOutBound()");

    }

    @Override
    public void channelAfterAllOutBound(ChannelHandlerContext ctx) throws Exception {
      System.out.println("ctx.channelAfterAllOutBound()");

    }
  }

  final class TailContext extends AbstractChannelHandlerContext implements ChannelInboundHandler {

    TailContext(DefaultChannelPipeline pipeline) {
      super("TAIL_NAME", true, false, pipeline);
    }

    @Override
    public ChannelHandler handler() {
      return this;
    }


    @Override
    public void channelBeforeAll(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelBeforeAll()");
    }

    @Override
    public void channelBeforeAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelBeforeAssemble()");
    }

    @Override
    public void channelAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelAssemble()");
    }

    @Override
    public void channelAfterAssemble(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelAfterAssemble()");
    }

    @Override
    public void channelBeforeRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelBeforeRequest()");
    }

    @Override
    public void channelRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelRequest()");
    }

    @Override
    public void channelAfterRequest(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelAfterRequest()");
    }

    @Override
    public void channelBeforeRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelBeforeRemould()");
    }

    @Override
    public void channelRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelRemould()");
    }

    @Override
    public void channelAfterRemould(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelAfterRemould()");
    }

    @Override
    public void channelAfterAll(ChannelHandlerContext ctx) throws Exception {
      System.out.println("末尾:ctx.channelAfterAll()");
    }

  }

}
