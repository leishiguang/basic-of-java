
package demo.netty.pipeline.util;


/**
 * Key which can be used to access {@link Attribute} out of the {@link AttributeMap}. Be aware that
 * it is not be possible to have multiple keys with the same name.
 *
 * @param <T> the type of the {@link Attribute} which can be accessed via this {@link
 *            AttributeKey}.
 */
@SuppressWarnings("UnusedDeclaration") // 'T' is used only at compile time
public final class AttributeKey<T> extends AbstractConstant<AttributeKey<T>> {

  private static final ConstantPool<AttributeKey<Object>> pool = new ConstantPool<AttributeKey<Object>>() {
    @Override
    protected AttributeKey<Object> newConstant(int id, String name) {
      return new AttributeKey<Object>(id, name);
    }
  };

  /**
   * Returns the singleton instance of the {@link AttributeKey} which has the specified {@code
   * name}.
   */
  @SuppressWarnings("unchecked")
  public static <T> AttributeKey<T> valueOf(String name) {
    return (AttributeKey<T>) pool.valueOf(name);
  }

  /**
   * Returns {@code true} if a {@link AttributeKey} exists for the given {@code name}.
   */
  public static boolean exists(String name) {
    return pool.exists(name);
  }

  /**
   * Creates a new {@link AttributeKey} for the given {@code name} or fail with an {@link
   * IllegalArgumentException} if a {@link AttributeKey} for the given {@code name} exists.
   */
  @SuppressWarnings("unchecked")
  public static <T> AttributeKey<T> newInstance(String name) {
    return (AttributeKey<T>) pool.newInstance(name);
  }

  @SuppressWarnings("unchecked")
  public static <T> AttributeKey<T> valueOf(
      Class<?> firstNameComponent, String secondNameComponent) {
    return (AttributeKey<T>) pool.valueOf(firstNameComponent, secondNameComponent);
  }

  private AttributeKey(int id, String name) {
    super(id, name);
  }
}
