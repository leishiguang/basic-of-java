package demo.netty.pipeline.channel;

import lombok.extern.slf4j.Slf4j;

/**
 * 默认的异常处理，放到通道的参数中，等待后续处理
 *
 * @author leishiguang
 * @since v1.0
 */
@Slf4j
public class DefaultErrorHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelExceptionCaught(ChannelHandlerContext ctx, Throwable caugth) {
    //把异常信息放到通道中，在获取数据的时候如果未处理，则进行抛出；
    log.debug("由默认的异常处理Handler，终止了请求的处理");
    ctx.channel().stop();
    ctx.channel().throwable(caugth);
  }
}
