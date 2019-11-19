package demo.netty.pipeline.channel;


import static demo.netty.pipeline.channel.ChannelOption.CHANNEL_ID;
import static demo.netty.pipeline.channel.ChannelOption.CHANNEL_MODE;
import static demo.netty.pipeline.channel.ChannelOption.CHANNEL_NAME;

import demo.netty.pipeline.channel.ChannelOption.ChannelMode;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 默认的通道配置信息
 *
 * @author leishiguang
 * @since v1.0
 */
public class DefaultChannelConfig implements ChannelConfig {

  protected final Channel channel;

  protected volatile String channelName = "defaultChannelName";
  protected volatile String channelId = "defaultChannelId";
  protected volatile ChannelMode channelMode;

  public DefaultChannelConfig(Channel channel) {
    this.channel = channel;
  }

  /**
   * 返回所有配置
   *
   * @return Map<String, Object>
   */
  @Override
  public Map<ChannelOption<?>, Object> getOptions() {
    return getOptions(null, CHANNEL_NAME, CHANNEL_ID, CHANNEL_MODE);
  }

  protected Map<ChannelOption<?>, Object> getOptions(
      Map<ChannelOption<?>, Object> result, ChannelOption<?>... options) {
    if (result == null) {
      result = new IdentityHashMap<>();
    }
    for (ChannelOption<?> o : options) {
      result.put(o, getOption(o));
    }
    return result;
  }

  /**
   * 返回某个配置
   *
   * @param option@return 配置
   */
  @Override
  @SuppressWarnings({"unchecked"})
  public <T> T getOption(ChannelOption<T> option) {
    if (option == null) {
      throw new NullPointerException("option");
    }
    if (option == CHANNEL_NAME) {
      return (T) channelName;
    }
    if (option == CHANNEL_ID) {
      return (T) channelId;
    }
    if (option == CHANNEL_MODE) {
      return (T) channelMode;
    }
    return null;
  }

  /**
   * 配置某个键值
   *
   * @return 是否配置成功
   */
  @Override
  public <T> boolean setOption(ChannelOption<T> option, T value) {
    if (option == CHANNEL_NAME) {
      channelName = (String) value;
    } else if (option == CHANNEL_ID) {
      channelId = (String) value;
    } else if (option == CHANNEL_MODE) {
      channelMode = (ChannelMode) value;
    } else {
      return false;
    }
    return true;
  }


}
