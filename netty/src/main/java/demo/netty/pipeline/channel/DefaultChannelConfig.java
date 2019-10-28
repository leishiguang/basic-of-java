package demo.netty.pipeline.channel;

import java.util.Map;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelConfig implements ChannelConfig {

  protected final Channel channel;


  public DefaultChannelConfig(Channel channel) {
    this.channel = channel;
  }

  /**
   * 返回所有配置
   *
   * @return Map<String, Object>
   */
  @Override
  public Map<String, Object> getOptions() {
    return null;
  }

  /**
   * 返回某个配置
   *
   * @param key   配置key
   * @param clazz 配置键的类型
   * @return 配置
   */
  @Override
  public <T> T getOption(String key, Class<T> clazz) {
    return null;
  }

  /**
   * 配置某个键值
   *
   * @param key   配置key
   * @param value 配置键的类型
   * @return 是否配置成功
   */
  @Override
  public <T> boolean setOption(String key, T value) {
    return false;
  }
}
