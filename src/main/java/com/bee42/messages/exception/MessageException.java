package com.bee42.messages.exception;

import com.bee42.messages.MessageType;
import com.bee42.messages.MessageFormatter;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Super class for all business exceptions.
 */
public class MessageException extends RuntimeException {

  private MessageType errorMessage;

  private Object[] args;

  private String stackTrace;

  /**
   * Create an MessageException with a message without placeholders.
   *
   * @param errorMessage the error message (e.g. an Enum)
   */
  public MessageException(final MessageType errorMessage) {

    super(errorMessage.getLogMessage());
    this.errorMessage = errorMessage;
  }

  /**
   * Create an MessageException with a non parameterized message and a causing
   * exception.
   *
   * @param errorMessage the error message (e.g. an Enum)
   * @param cause        the causing Exception
   */
  public MessageException(final MessageType errorMessage, final Throwable cause) {

    this(errorMessage);
    initCause(cause);

  }

  /**
   * Create an MessageException with a parameterized error message.
   *
   * @param errorMessage the error message (e.g. an Enum)
   * @param args         the parameters that fill in the place holders in the
   *                     error message.
   */
  public MessageException(final MessageType errorMessage, Object... args) {

    this(errorMessage);
    this.args = args;

  }

  /**
   * Create an MessageException with a parameterized error message.
   *
   * @param errorMessage the error message (e.g. an Enum)
   * @param cause        the causing Exception
   * @param args         the parameters that fill in the place holders in the
   *                     error message.
   */
  public MessageException(final MessageType errorMessage, Throwable cause, Object... args) {

    this(errorMessage, args);
    initCause(cause);

  }

  /**
   * Only report cause it exists!
   */
  private void handleCause() {
    Throwable cause = getCause();
    final StringWriter sw = new StringWriter();
    if (cause != null) {
      cause.printStackTrace(new PrintWriter(sw));
    } else {
      printStackTrace(new PrintWriter(sw));
    }
    stackTrace = sw.toString();
  }

  /**
   * Get the error message.
   *
   * @return the MessageType. Usually, this is an Enum of type MessageType.
   */
  public MessageType getErrorMessage() {

    return errorMessage;
  }

  /**
   * Get the arguments that parameterize the error message.
   *
   * @return the array of arguments
   */
  public Object[] getArgs() {

    return args;
  }

  /**
   * Get the message of the exception.
   *
   * @return the parameterized message as a string.
   */
  @Override
  public String getMessage() {
    return MessageFormatter.format(errorMessage, args);
  }

  public String getStackTraceAsString() {
    if (stackTrace == null)
      handleCause();
    return stackTrace;
  }

}
