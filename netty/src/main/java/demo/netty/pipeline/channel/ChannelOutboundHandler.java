package demo.netty.pipeline.channel;

/**
 * 出站处理器接口
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelOutboundHandler extends ChannelHandler {

  void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeAssembleOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelAssembleOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelAfterAssembleOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeRequestOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelRequestOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelAfterRequestOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelBeforeRemouldOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelRemouldOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelAfterRemouldOutBound(ChannelHandlerContext ctx) throws Exception;

  void channelAfterAllOutBound(ChannelHandlerContext ctx) throws Exception;

}
