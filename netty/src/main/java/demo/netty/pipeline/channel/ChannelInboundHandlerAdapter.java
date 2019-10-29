package demo.netty.pipeline.channel;

/**
 * {@link ChannelInboundHandler} 的适配器实现，实现了所有的方法。
 * <p>
 * 子类可以通过通过复写当前类的方法，改变{@link ChannelPipeline}的逻辑。
 *
 * @author leishiguang
 * @since v1.0
 */
public class ChannelInboundHandlerAdapter extends AbstractChannelHandlerAdapter implements
    ChannelInboundHandler {

  @Override
  public void channelInitalizer(ChannelHandlerContext ctx) throws Exception {
    ctx.initalizer();
  }

  @Override
  public void channelBeforeAll(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeAll();
  }

  @Override
  public void channelBeforeAssemble(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeAssemble();
  }

  @Override
  public void channelAssemble(ChannelHandlerContext ctx) throws Exception {
    ctx.assemble();
  }

  @Override
  public void channelAfterAssemble(ChannelHandlerContext ctx) throws Exception {
    ctx.afterAssemble();
  }

  @Override
  public void channelBeforeRequest(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeRequest();
  }

  @Override
  public void channelRequest(ChannelHandlerContext ctx) throws Exception {
    ctx.request();
  }

  @Override
  public void channelAfterRequest(ChannelHandlerContext ctx) throws Exception {
    ctx.afterRequest();
  }

  @Override
  public void channelBeforeRemould(ChannelHandlerContext ctx) throws Exception {
    ctx.beforeRemould();
  }

  @Override
  public void channelRemould(ChannelHandlerContext ctx) throws Exception {
    ctx.remould();
  }

  @Override
  public void channelAfterRemould(ChannelHandlerContext ctx) throws Exception {
    ctx.afterRemould();
  }

  @Override
  public void channelAfterAll(ChannelHandlerContext ctx) throws Exception {
    ctx.afterAll();
  }
}
