package demo.netty.pipeline.exception;

/**
 * 在通道中发生的异常
 *
 * @author leishiguang
 * @since v1.0
 */
public class ChannelException extends RuntimeException {

  public ChannelException(String message) {
    super(message);
  }

  public ChannelException(String message, Throwable cause) {
    super(message, cause);
  }

  public ChannelException(Throwable cause) {
    super(cause);
  }

  public ChannelException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
