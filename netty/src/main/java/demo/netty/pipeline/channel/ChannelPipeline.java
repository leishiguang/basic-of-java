package demo.netty.pipeline.channel;

import java.util.Map;
import java.util.Map.Entry;

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
   * 在职责链的末尾，添加一个新的处理类
   *
   * @param name    处理器名称
   * @param handler 处理器
   * @return ChannelPipeline
   */
  ChannelPipeline addLast(String name, ChannelHandler handler);

  /**
   * 将职责链转化为有序的 map，key为名称，value 为 handler
   *
   * @return Map<String, ChannelHandler>
   */
  Map<String, ChannelHandler> toMap();

  /**
   * 入站的数据准备
   */
  @Override
  ChannelPipeline beforeAll();

  /**
   * 执行入站请求
   */
  @Override
  ChannelPipeline request();
}
