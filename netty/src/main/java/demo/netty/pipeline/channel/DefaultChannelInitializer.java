package demo.netty.pipeline.channel;

/**
 * 执行通道的初始化，比如，添加默认的配置、pipeline
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelInitializer extends AbstractChannelInitializer<DefaultChannel> {

  @Override
  protected void initChannel(DefaultChannel ch) throws Exception {
    System.out.println("DefaultChannelInitializer.initChannel");
    ChannelPipeline p = ch.pipeline();
    p.addLast(new DefaultLogDuplexHandler());
  }

}
