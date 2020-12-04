package com.bee42.messages.exception;

import static org.junit.Assert.assertEquals;

import com.bee42.messages.Parameter;
import org.junit.Test;

public class MessageExceptionTest {

  @Test
  public void testException() {
    MessageException exception1 = new MessageException(ErrorMessages.GENERAL_ERROR);
    assertEquals(ErrorMessages.GENERAL_ERROR.getCode() + ": " + ErrorMessages.GENERAL_ERROR.getLogMessage(),
        exception1.getMessage());
    assertEquals("com.bee42.messages.exception.MessageException: SYSTEM_001:",
        exception1.getStackTraceAsString().substring(0, 58));
  }

  @Test
  public void testParameter() {
    Parameter[] parameters = ErrorMessages.NOT_FOUND_ERROR.getParameters();
    assertEquals(1, parameters.length);
    assertEquals("resource", parameters[0].getName());
  }
}
