package demo.netty.pipeline.channel;

import demo.netty.pipeline.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于初始化通道
 *
 * @author leishiguang
 * @since v1.0
 */
public abstract class AbstractChannelInitializer<C extends Channel> extends
    ChannelInboundHandlerAdapter {

  private final ConcurrentMap<ChannelHandlerContext, Boolean> initMap = PlatformDependent
      .newConcurrentHashMap();

  protected abstract void initChannel(C ch) throws Exception;

  @Override
  public void channelInitalizer(ChannelHandlerContext ctx) throws Exception {
    initChannel(ctx);
    super.channelInitalizer(ctx);
  }

  @SuppressWarnings("unchecked")
  private boolean initChannel(ChannelHandlerContext ctx) throws Exception {
    if (initMap.putIfAbsent(ctx, Boolean.TRUE) == null) { // Guard against re-entrance.
      try {// Tony: 这个init方法一般就是创建channel时，实现的那个initchannel方法
        initChannel((C) ctx.channel());
      } catch (Throwable cause) {
        System.err.println("未完成职责链的初始化");
        cause.printStackTrace();
      } finally {// Tony: ChannelInitializer执行结束之后，会把自己从pipeline中删除掉，避免重复初始化
        remove(ctx);
      }
      return true;
    }
    return false;
  }

  private void remove(ChannelHandlerContext ctx) {
    try {
      ChannelPipeline pipeline = ctx.pipeline();
      if (pipeline.context(this) != null) {
        //动态删除
        pipeline.remove(this);
      }
    } finally {
      initMap.remove(ctx);
    }
  }
}
