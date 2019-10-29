package demo.netty.pipeline.channel;

/**
 * 入站处理器
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelInboundInvoker {

  ChannelInboundInvoker initalizer();

  /**
   * 入站前的请求编码
   */
  ChannelInboundInvoker beforeAll();

  /**
   * 组装
   */
  ChannelInboundInvoker beforeAssemble();

  /**
   * 组装
   */
  ChannelInboundInvoker assemble();

  /**
   * 组装之后
   */
  ChannelInboundInvoker afterAssemble();

  /**
   * 请求之前
   */
  ChannelInboundInvoker beforeRequest();

  /**
   * 执行请求
   */
  ChannelInboundInvoker request();

  /**
   * 请求之后
   */
  ChannelInboundInvoker afterRequest();

  /**
   * 改造响应数据之前
   */
  ChannelInboundInvoker beforeRemould();

  /**
   * 改造响应数据
   */
  ChannelInboundInvoker remould();

  /**
   * 改造相应数据之后
   */
  ChannelInboundInvoker afterRemould();

  /**
   * 入站请求之后
   */
  ChannelInboundInvoker afterAll();


}
