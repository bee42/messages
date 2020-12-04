package com.bee42.messages.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.PropertyDefinerBase;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * <configuration>
 * <define name="X-PID" class="messages.logback.PidDefiner" scope="system" />
 * <appender name="LOGGER" class="ch.qos.logback.core.ConsoleAppender">
 * <encoder> <charset>utf-8</charset>
 * <pattern>{}MESSAGE[%property{X-pid}];%d{yyyy-MM-dd'T'HH:mm:ss.SSSZ};1.2;%mdc{hostname:--};%thread;%mdc{system:--};%mdc{correlationID:--};%mdc{requestID:--};%-5level;%logger{1};[%msg]%n%xEx</pattern>
 * </encoder> </appender> </configuration>
 */
public class PidDefiner extends PropertyDefinerBase {

  @Override
  public String getPropertyValue() {
    return getProcessId("-");
  }

  /**
   * Note: may fail in some JVM implementations therefore fallback has to be
   * provided
   * http://stackoverflow.com/questions/35842/how-can-a-java-program-get-its-own-process-id
   */
  protected static String getProcessId(final String fallback) {

    // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
    final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
    final int index = jvmName.indexOf('@');

    if (index < 1) {
      // part before '@' empty (index = 0) / '@' not found (index = -1)
      return fallback;
    }

    try {
      return Long.toString(Long.parseLong(jvmName.substring(0, index)));
    } catch (NumberFormatException e) {
      // ignore
    }
    return fallback;
  }
}
