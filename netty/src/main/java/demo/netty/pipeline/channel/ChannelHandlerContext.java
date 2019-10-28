package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.AttributeMap;

/**
 * 职责链的管理上下文
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelHandlerContext extends AttributeMap, ChannelInboundInvoker,
    ChannelOutboundInvoker {

  /**
   * 当前职责链的名称
   *
   * @return 名称
   */
  String name();

  /**
   * 当前绑定的处理类
   *
   * @return ChannelHandler
   */
  ChannelHandler handler();

  /**
   * Return the assigned {@link ChannelPipeline}
   */
  ChannelPipeline pipeline();


  /**
   * 入站前的请求编码
   */
  @Override
  ChannelHandlerContext beforeAll();


  /**
   * 组装
   */
  @Override
  ChannelHandlerContext beforeAssemble();

  /**
   * 组装
   */
  @Override
  ChannelHandlerContext assemble();

  /**
   * 组装之后
   */
  @Override
  ChannelHandlerContext afterAssemble();

  /**
   * 请求之前
   */
  @Override
  ChannelHandlerContext beforeRequest();

  /**
   * 执行请求
   */
  @Override
  ChannelHandlerContext request();

  /**
   * 请求之后
   */
  @Override
  ChannelHandlerContext afterRequest();

  /**
   * 改造响应数据之前
   */
  @Override
  ChannelHandlerContext beforeRemould();

  /**
   * 改造响应数据
   */
  @Override
  ChannelHandlerContext remould();

  /**
   * 改造相应数据之后
   */
  @Override
  ChannelHandlerContext afterRemould();

  /**
   * 入站请求之后
   */
  @Override
  ChannelHandlerContext afterAll();


  /**
   * 出站前的请求编码
   */
  @Override
  ChannelHandlerContext beforeAllOutBound();

  /**
   * 组装
   */
  @Override
  ChannelHandlerContext beforeAssembleOutBound();

  /**
   * 组装
   */
  @Override
  ChannelHandlerContext assembleOutBound();

  /**
   * 组装之后
   */
  @Override
  ChannelHandlerContext afterAssembleOutBound();

  /**
   * 请求之前
   */
  @Override
  ChannelHandlerContext beforeRequestOutBound();

  /**
   * 执行请求
   */
  @Override
  ChannelHandlerContext requestOutBound();

  /**
   * 请求之后
   */
  @Override
  ChannelHandlerContext afterRequestOutBound();

  /**
   * 改造响应数据之前
   */
  @Override
  ChannelHandlerContext beforeRemouldOutBound();

  /**
   * 改造响应数据
   */
  @Override
  ChannelHandlerContext remouldOutBound();

  /**
   * 改造相应数据之后
   */
  @Override
  ChannelHandlerContext afterRemouldOutBound();

  /**
   * 请求之后
   */
  @Override
  ChannelHandlerContext afterAllOutBound();
}
