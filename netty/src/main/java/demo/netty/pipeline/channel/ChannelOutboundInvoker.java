package demo.netty.pipeline.channel;

/**
 * 出站处理器
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelOutboundInvoker {

  /**
   * 入站前的请求编码
   */
  ChannelOutboundInvoker beforeAllOutBound();


  /**
   * 组装
   */
  ChannelOutboundInvoker beforeAssembleOutBound();

  /**
   * 组装
   */
  ChannelOutboundInvoker assembleOutBound();

  /**
   * 组装之后
   */
  ChannelOutboundInvoker afterAssembleOutBound();

  /**
   * 请求之前
   */
  ChannelOutboundInvoker beforeRequestOutBound();


  /**
   * 执行请求
   */
  ChannelOutboundInvoker requestOutBound();

  /**
   * 请求之后
   */
  ChannelOutboundInvoker afterRequestOutBound();

  /**
   * 改造响应数据之前
   */
  ChannelOutboundInvoker beforeRemouldOutBound();

  /**
   * 改造响应数据
   */
  ChannelOutboundInvoker remouldOutBound();

  /**
   * 改造相应数据之后
   */
  ChannelOutboundInvoker afterRemouldOutBound();

  /**
   * 入站请求之后
   */
  ChannelOutboundInvoker afterAllOutBound();



}
