package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.AbstractConstant;
import demo.netty.pipeline.util.ConstantPool;

/**
 * 通道的配置信息
 *
 * @author leishiguang
 * @since v1.0
 */
public class ChannelOption<T> extends AbstractConstant<ChannelOption<T>> {

  private static final ConstantPool<ChannelOption<Object>> pool = new ConstantPool<ChannelOption<Object>>() {
    @Override
    protected ChannelOption<Object> newConstant(int id, String name) {
      return new ChannelOption<Object>(id, name);
    }
  };

  /**
   * Returns the {@link ChannelOption} of the specified name.
   */
  @SuppressWarnings("unchecked")
  public static <T> ChannelOption<T> valueOf(String name) {
    return (ChannelOption<T>) pool.valueOf(name);
  }

  /**
   * Shortcut of {@link #valueOf(String) valueOf(firstNameComponent.getName() + "#" +
   * secondNameComponent)}.
   */
  @SuppressWarnings("unchecked")
  public static <T> ChannelOption<T> valueOf(Class<?> firstNameComponent,
      String secondNameComponent) {
    return (ChannelOption<T>) pool.valueOf(firstNameComponent, secondNameComponent);
  }

  /**
   * Returns {@code true} if a {@link ChannelOption} exists for the given {@code name}.
   */
  public static boolean exists(String name) {
    return pool.exists(name);
  }

  /**
   * Creates a new {@link ChannelOption} for the given {@code name} or fail with an {@link
   * IllegalArgumentException} if a {@link ChannelOption} for the given {@code name} exists.
   */
  @SuppressWarnings("unchecked")
  public static <T> ChannelOption<T> newInstance(String name) {
    return (ChannelOption<T>) pool.newInstance(name);
  }


  /**
   * Creates a new {@link ChannelOption} with the specified unique {@code name}.
   */
  private ChannelOption(int id, String name) {
    super(id, name);
  }

  public static final ChannelOption<String> CHANNEL_NAME = valueOf("channelName");
  public static final ChannelOption<String> CHANNEL_ID = valueOf("channelId");
}
