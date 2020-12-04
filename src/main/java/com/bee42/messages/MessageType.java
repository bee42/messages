package com.bee42.messages;

public interface MessageType {

  /**
   * The unique message code.
   *
   * @return the code and never null
   */
  public String getCode();

  /**
   * The message text in English locale.
   *
   * @return the text and never null
   */
  public String getLogMessage();

  /**
   * A Description of the message and its arguments. This is purely intended for
   * documentation.
   *
   * @return the description of the message
   */
  public String getDescription();

}