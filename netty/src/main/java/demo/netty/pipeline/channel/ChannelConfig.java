package demo.netty.pipeline.channel;

import java.util.Map;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public interface ChannelConfig {

  /**
   * 返回所有配置
   *
   * @return Map<String, Object>
   */
  Map<ChannelOption<?>, Object> getOptions();

  /**
   * 返回某个配置
   *
   * @param <T>   配置键的类型
   * @return 配置
   */
  <T> T getOption(ChannelOption<T> option);

  /**
   * 配置某个键值
   *
   * @param value 配置键的类型
   * @param <T>   配置键的类型
   * @return 是否配置成功
   */
  <T> boolean setOption(ChannelOption<T> option, T value);
}
