package com.bee42.messages.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class ThreadIdConverterTest {

  @Test
  public void testNormalThreadName() {
    ILoggingEvent mockEvent = mock(ILoggingEvent.class);

    ThreadIdConverter converter = new ThreadIdConverter();
    converter.setOptionList(null);
    converter.start();
    String name = Thread.currentThread().getName();
    when(mockEvent.getThreadName()).thenReturn(name);

    String result = converter.convert(mockEvent);
    assertEquals(name, result);
  }

  @Test
  public void testNameAndId() {
    ILoggingEvent mockEvent = mock(ILoggingEvent.class);

    ThreadIdConverter converter = new ThreadIdConverter();
    List<String> options = new ArrayList<String>();
    options.add("id");
    options.add("name");
    converter.setOptionList(options);
    converter.start();
    String name = Thread.currentThread().getName();
    when(mockEvent.getThreadName()).thenReturn(name);

    String result = converter.convert(mockEvent);
    assertEquals(Thread.currentThread().getId() + ":" + name, result);
  }

}
