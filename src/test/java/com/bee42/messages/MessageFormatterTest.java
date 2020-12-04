package com.bee42.messages;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MessageFormatterTest {

  @Test
  public void testFormat() {

    assertEquals("12: Say Hello", MessageFormatter.format(new MessageType() {
      @Override
      public String getCode() {

        return "12";
      }

      @Override
      public String getLogMessage() {

        return "Say {}";
      }

      @Override
      public String getDescription() {

        return "This will say hello";
      }
    }, "Hello"));
  }
}
