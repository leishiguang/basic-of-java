package demo.netty.pipeline.util;


/**
 * Holds {@link Attribute}s which can be accessed via {@link AttributeKey}.
 * <p>
 * Implementations must be Thread-safe.
 */
public interface AttributeMap {

  /**
   * Get the {@link Attribute} for the given {@link AttributeKey}. This method will never return
   * null, but may return an {@link Attribute} which does not have a value set yet.
   */
  <T> Attribute<T> attr(AttributeKey<T> key);

  /**
   * Returns {@code} true if and only if the given {@link Attribute} exists in this {@link
   * AttributeMap}.
   */
  <T> boolean hasAttr(AttributeKey<T> key);
}
