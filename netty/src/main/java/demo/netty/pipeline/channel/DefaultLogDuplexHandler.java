package demo.netty.pipeline.channel;


/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultLogDuplexHandler extends SimpleChannelDuplexHandler {


  @Override
  public void channelBeforeAll(ChannelHandlerContext ctx) throws Exception {
    System.out.println("入站：在全部开始之前，记录日志");
    ctx.beforeAll();
  }

  @Override
  public void channelAfterAll(ChannelHandlerContext ctx) throws Exception {
    System.out.println("入站：在全部开始之前，记录日志");
    ctx.afterAll();
  }

  @Override
  public void channelBeforeAllOutBound(ChannelHandlerContext ctx) throws Exception {
    System.out.println("出站：在全部开始之前，记录日志");
    ctx.beforeAllOutBound();
  }

  @Override
  public void channelAfterAllOutBound(ChannelHandlerContext ctx) throws Exception {
    System.out.println("出站：在全部完成之后，记录日志");
    ctx.afterAllOutBound();
  }

}
