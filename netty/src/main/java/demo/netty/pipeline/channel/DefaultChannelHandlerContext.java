package demo.netty.pipeline.channel;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelHandlerContext extends AbstractChannelHandlerContext {

  private final ChannelHandler handler;

  DefaultChannelHandlerContext(
      DefaultChannelPipeline pipeline, String name, ChannelHandler handler) {
    super(name, isInbound(handler), isOutbound(handler), pipeline);
    if (handler == null) {
      throw new NullPointerException("handler");
    }
    this.handler = handler;
  }

  /**
   * 当前绑定的处理类
   *
   * @return ChannelHandler
   */
  @Override
  public ChannelHandler handler() {
    return handler;
  }


  private static boolean isInbound(ChannelHandler handler) {
    return handler instanceof ChannelInboundHandler;
  }

  private static boolean isOutbound(ChannelHandler handler) {
    return handler instanceof ChannelOutboundHandler;
  }
}
