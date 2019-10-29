package demo.netty.pipeline.channel;


import demo.netty.pipeline.util.internal.StringUtil;

/**
 * 创建通道的简单工厂
 *
 * @author leishiguang
 * @since v1.0
 */
public class ReflectiveChannelFactory<T extends Channel> implements ChannelFactory<T> {

  private final Class<? extends T> clazz;

  public ReflectiveChannelFactory(Class<? extends T> clazz) {
    if (clazz == null) {
      throw new NullPointerException("clazz");
    }
    this.clazz = clazz;
  }

  @Override
  public T newChannel() {
    try {
      return clazz.getConstructor().newInstance();
    } catch (Throwable t) {
      throw new RuntimeException("Unable to create Channel from class " + clazz, t);
    }
  }

  @Override
  public String toString() {
    return StringUtil.simpleClassName(clazz) + ".class";
  }
}
