package demo.netty.pipeline.channel;

import com.sun.javafx.binding.StringFormatter;

import demo.netty.pipeline.util.AttributeKey;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通道初始化的引导
 *
 * @author leishiguang
 * @since v1.0
 */
public abstract class AbstractBootstrap<B extends AbstractBootstrap<B, C>, C extends Channel> {

  private volatile ChannelFactory<? extends C> channelFactory;
  private final Map<ChannelOption<?>, Object> options = new LinkedHashMap<>();
  private final Map<AttributeKey<?>, Object> attrs = new LinkedHashMap<>();
  private volatile ChannelHandler handler;

  AbstractBootstrap() {
    // Disallow extending from a different package.
  }

  AbstractBootstrap(AbstractBootstrap<B, C> bootstrap) {
    handler = bootstrap.handler;
    synchronized (bootstrap.options) {
      options.putAll(bootstrap.options);
    }
    synchronized (bootstrap.attrs) {
      attrs.putAll(bootstrap.attrs);
    }
  }

  /**
   * 设置通道的创建工厂
   */
  public B channel(Class<? extends C> channelClass) {
    if (channelClass == null) {
      throw new NullPointerException("channelClass");
    }
    if (this.channelFactory != null) {
      throw new IllegalStateException("channelFactory set already");
    }
    this.channelFactory = new ReflectiveChannelFactory<C>(channelClass);
    return self();
  }


  /**
   * 通道的 option 配置
   */
  public <T> B option(ChannelOption<T> option, T value) {
    if (option == null) {
      throw new NullPointerException("option");
    }
    if (value == null) {
      synchronized (options) {
        options.remove(option);
      }
    } else {
      synchronized (options) {
        options.put(option, value);
      }
    }
    return self();
  }

  /**
   * 通道中的参数
   */
  public <T> B attr(AttributeKey<T> key, T value) {
    if (key == null) {
      throw new NullPointerException("key");
    }
    if (value == null) {
      synchronized (attrs) {
        attrs.remove(key);
      }
    } else {
      synchronized (attrs) {
        attrs.put(key, value);
      }
    }
    return self();
  }

  public B handler(ChannelHandler handler) {
    if (handler == null) {
      throw new NullPointerException("handler");
    }
    this.handler = handler;
    return self();
  }

  /**
   * 执行校验
   */
  public B validate() {
    if (channelFactory == null) {
      throw new IllegalStateException("channel or channelFactory not set");
    }
    return self();
  }

  @SuppressWarnings("unchecked")
  private B self() {
    return (B) this;
  }

  /**
   * 执行初始化
   */
  public C start() {
    C channel = null;
    try {
      channel = channelFactory.newChannel();
      init(channel);
      //发布通道初始化的事件
      channel.pipeline().initalizer();
    } catch (Throwable t) {
      System.err.println("初始化失败");
      t.printStackTrace();
    }
    return channel;
  }

  static void setChannelOptions(
      Channel channel, Map<ChannelOption<?>, Object> options) {
    for (Map.Entry<ChannelOption<?>, Object> e : options.entrySet()) {
      setChannelOption(channel, e.getKey(), e.getValue());
    }
  }

  @SuppressWarnings("unchecked")
  private static void setChannelOption(
      Channel channel, ChannelOption<?> option, Object value) {
    try {
      if (!channel.config().setOption((ChannelOption<Object>) option, value)) {
        System.out.println(StringFormatter.format("Unknown channel option '{}' for channel '{}'", option, channel));
      }
    } catch (Throwable t) {
      System.out.println(StringFormatter.format(
          "Failed to set channel option '{}' with value '{}' for channel '{}'", option, value,
          channel));
      t.printStackTrace();
    }
  }

  final ChannelHandler handler0() {
    return handler;
  }

  final Map<ChannelOption<?>, Object> options0() {
    return options;
  }

  final Map<AttributeKey<?>, Object> attrs0() {
    return attrs;
  }

  abstract void init(Channel channel) throws Exception;
}
