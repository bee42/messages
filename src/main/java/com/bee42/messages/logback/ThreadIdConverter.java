package com.bee42.messages.logback;

import java.util.List;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 
 * <conversionRule conversionWord="th" converterClass=
 * "messages.logback.ThreadIdConverter" />
 * 
 * <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
 * <encoder> <pattern>%.. [%th{name,id}] %msg%n</pattern> </encoder> </appender>
 * 
 * 
 * 2013-06-10T13:49:38.000931+0200 ERROR [RegistrationService-Executor-4:267] :
 * Backend Service 'TCS' is unavailable with reason 'java.net.ConnectException:
 * Connection refused'
 * 
 */
public class ThreadIdConverter extends ClassicConverter {
  private List<String> options = null;

  @Override
  public void start() {
    options = getOptionList();
    super.start();
  }

  @Override
  public void stop() {
    options = null;
    super.stop();
  }

  @Override
  public String convert(ILoggingEvent event) {
    if (options == null || options.size() < 1)
      return event.getThreadName();
    else {
      StringBuilder builder = new StringBuilder();
      int index = 1;
      for (String option : options) {
        if ("id".equals(option))
          builder.append(Thread.currentThread().getId());
        else if ("name".equals(option))
          builder.append(event.getThreadName());
        if (index < options.size())
          builder.append(":");
        index++;
      }
      return builder.toString();
    }
  }
}
