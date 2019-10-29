package demo.netty.pipeline.channel;

/**
 * 创建一个新通道{@link Channel}
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelFactory<T extends Channel> {

  T newChannel();
}
