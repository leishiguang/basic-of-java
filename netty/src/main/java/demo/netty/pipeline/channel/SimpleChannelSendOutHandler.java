package demo.netty.pipeline.channel;

/**
 * 执行数据发送的Handler，对外部进行通知的时候，会依次执行下列所有方法
 *
 * @author leishiguang
 * @since v1.0
 */
public class SimpleChannelSendOutHandler extends SimpleChannelDuplexHandler {

  @Override
  public void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelBeforeAllOutBound(ctx);
  }

  @Override
  public void channelBeforeAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelBeforeAssembleOutBound(ctx);
  }

  @Override
  public void channelAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelAssembleOutBound(ctx);
  }

  @Override
  public void channelAfterAssembleOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelAfterAssembleOutBound(ctx);
  }

  @Override
  public void channelBeforeRequestOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelBeforeRequestOutBound(ctx);
  }

  @Override
  public void channelRequestOutBound(ChannelHandlerContext ctx) throws Exception {
    super.channelRequestOutBound(ctx);
  }

  @Override
  public void channelAfterRequest(ChannelHandlerContext ctx) throws Exception {
    super.channelAfterRequest(ctx);
  }

  @Override
  public void channelBeforeRemould(ChannelHandlerContext ctx) throws Exception {
    super.channelBeforeRemould(ctx);
  }

  @Override
  public void channelRemould(ChannelHandlerContext ctx) throws Exception {
    super.channelRemould(ctx);
  }

  @Override
  public void channelAfterRemould(ChannelHandlerContext ctx) throws Exception {
    super.channelAfterRemould(ctx);
  }

  @Override
  public void channelAfterAll(ChannelHandlerContext ctx) throws Exception {
    super.channelAfterAll(ctx);
  }
}
