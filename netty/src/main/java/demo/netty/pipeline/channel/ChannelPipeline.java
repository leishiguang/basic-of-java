package demo.netty.pipeline.channel;

import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

/**
 * 通道的职责链
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelPipeline extends ChannelInboundInvoker, ChannelOutboundInvoker,
    Iterable<Entry<String, ChannelHandler>> {

  /**
   * 在职责链的开头，添加一个新的处理类
   *
   * @param name    处理器名称
   * @param handler 处理器
   * @return ChannelPipeline
   */
  ChannelPipeline addFirst(String name, ChannelHandler handler);

  /**
   * Inserts {@link ChannelHandler}s at the first position of this pipeline.
   *
   * @param handlers the handlers to insert first
   */
  ChannelPipeline addFirst(ChannelHandler... handlers);

  /**
   * 在职责链的末尾，添加一个新的处理类
   *
   * @param name    处理器名称
   * @param handler 处理器
   * @return ChannelPipeline
   */
  ChannelPipeline addLast(String name, ChannelHandler handler);

  /**
   * Inserts {@link ChannelHandler}s at the last position of this pipeline.
   *
   * @param handlers  the handlers to insert last
   *
   */
  ChannelPipeline addLast(ChannelHandler... handlers);

  /**
   * 将职责链转化为有序的 map，key为名称，value 为 handler
   *
   * @return Map<String, ChannelHandler>
   */
  Map<String, ChannelHandler> toMap();

  /**
   * Returns the {@link Channel} that this pipeline is attached to.
   *
   * @return the channel. {@code null} if this pipeline is not attached yet.
   */
  Channel channel();

  /**
   * Returns the context object of the specified {@link ChannelHandler} in this pipeline.
   *
   * @return the context object of the specified handler. {@code null} if there's no such handler in
   * this pipeline.
   */
  ChannelHandlerContext context(ChannelHandler handler);

  /**
   * Removes the specified {@link ChannelHandler} from this pipeline.
   *
   * @param handler the {@link ChannelHandler} to remove
   * @throws NoSuchElementException if there's no such handler in this pipeline
   * @throws NullPointerException   if the specified handler is {@code null}
   */
  ChannelPipeline remove(ChannelHandler handler);
}
