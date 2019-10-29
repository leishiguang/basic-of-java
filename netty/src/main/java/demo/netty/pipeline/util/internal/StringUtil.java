package demo.netty.pipeline.util.internal;

import static com.supermap.business.ow.api.base.util.internal.ObjectUtil.checkNotNull;

/**
 * todo: DESCRIPTION
 *
 * @author leishiguang
 * @since v1.0
 */
public class StringUtil {

  private static final char PACKAGE_SEPARATOR_CHAR = '.';

  public static String simpleClassName(Class<?> clazz) {
    String className = checkNotNull(clazz, "clazz").getName();
    final int lastDotIdx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
    if (lastDotIdx > -1) {
      return className.substring(lastDotIdx + 1);
    }
    return className;
  }

}
