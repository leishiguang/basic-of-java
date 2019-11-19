package demo.netty.pipeline.channel;

/**
 * 聚合的 Handler，是 {@link ChannelHandler} 的一个实现 聚合了 {@link ChannelInboundHandler} 和 {@link
 * ChannelOutboundHandler}； 如果新处理器即要支持出站，也要支持入站，则可以继承这个；
 *
 * <pre>
 * public class LogDuplexHandler extends SimpleChannelDuplexHandler {
 *
 *   public void channelBeforeAll(ChannelHandlerContext ctx) throws Exception {
 *     System.out.println("入站：在全部开始之前，记录日志");
 *     ctx.beforeAll();
 *   }
 *
 *   public void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception {
 *     System.out.println("出站：在全部开始之前，记录日志");
 *     ctx.beforeAllOutBound();
 *   }
 * }
 * </pre>
 *
 * @author leishiguang
 * @since v1.0
 */
public class SimpleChannelDuplexHandler extends ChannelInboundHandlerAdapter implements
    ChannelOutboundHandler {

  @Override
  public void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeAllOutBound();
  }

  @Override
  public void channelBeforeAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeAssembleOutBound();
  }

  @Override
  public void channelAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.assembleOutBound();
  }

  @Override
  public void channelAfterAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.afterAssembleOutBound();
  }

  @Override
  public void channelBeforeRequestOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeRequestOutBound();
  }

  @Override
  public void channelRequestOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.requestOutBound();
  }

  @Override
  public void channelAfterRequestOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.afterRequestOutBound();
  }

  @Override
  public void channelBeforeRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeRemouldOutBound();
  }

  @Override
  public void channelRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.remouldOutBound();
  }

  @Override
  public void channelAfterRemouldOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.afterRemouldOutBound();
  }

  @Override
  public void channelAfterAllOutBound(ChannelHandlerContext ctx) throws Exception {
    ctx.afterAllOutBound();
  }
}
