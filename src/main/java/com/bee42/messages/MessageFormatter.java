package com.bee42.messages;

/**
 * Simple formatter using the slf4j syntax ({}).
 */
public final class MessageFormatter {

  static {
    new MessageFormatter();
  }

  private MessageFormatter() {
    // nothing, just for metrics.
  }

  /**
   * Formats the argument message using the argument objects to fill the
   * placeholders.
   *
   * @param message with the text template
   * @param args    usings to fill the placeholders
   * @return the template with filled placeholders.
   */
  public static String format(MessageType message, Object... args) {

    return message.getCode() + ": "
        + org.slf4j.helpers.MessageFormatter.arrayFormat(message.getLogMessage(), args).getMessage();

  }
}