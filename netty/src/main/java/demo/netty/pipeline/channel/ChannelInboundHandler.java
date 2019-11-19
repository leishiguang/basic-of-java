package demo.netty.pipeline.channel;

/**
 * 入站处理器接口
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelInboundHandler extends ChannelHandler {

  void channelInitalizer(ChannelHandlerContext ctx) throws Exception;

  void channelExceptionCaught(ChannelHandlerContext ctx, Throwable caugth);

  void channelBeforeAll(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeAssemble(ChannelHandlerContext ctx) throws Exception;

  void channelAssemble(ChannelHandlerContext ctx) throws Exception;

  void channelAfterAssemble(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeRequest(ChannelHandlerContext ctx) throws Exception;

  void channelRequest(ChannelHandlerContext ctx) throws Exception;

  void channelAfterRequest(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeRemould(ChannelHandlerContext ctx) throws Exception;

  void channelRemould(ChannelHandlerContext ctx) throws Exception;

  void channelAfterRemould(ChannelHandlerContext ctx) throws Exception;

  void channelAfterAll(ChannelHandlerContext ctx) throws Exception;

}
