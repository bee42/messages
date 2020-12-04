package com.bee42.messages.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.PropertyDefinerBase;
import org.slf4j.impl.StaticLoggerBinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <configuration> <contextName>MESSAGE</contextName>
 * <define name="X-System" class="messages.logback.LoggerContextNameDefiner"
 * scope="context"> <path>versionInfo.properties</path> </define>
 * <appender name="LOGGER" class="ch.qos.logback.core.ConsoleAppender">
 * <encoder> <charset>utf-8</charset>
 * <pattern>%property{X-System}[%property{X-pid}];%d{yyyy-MM-dd'T'HH:mm:ss.SSSZ};1.2;%mdc{hostname:--};%thread;%property{X-System:--};%mdc{correlationID:--};%mdc{requestID:--};%-5level;%logger{1};[%msg]%n%xEx</pattern>
 * </encoder> </appender> </configuration>
 *
 * File: com.bee42.messages/logback/versionInfo.properties
 * application.name=Messages
 */
public class LoggerContextNameDefiner extends PropertyDefinerBase {

  private String path;

  public void setPath(final String path) {
    this.path = path;
  }

  @Override
  public String getPropertyValue() {
    if (path != null) {
      InputStream stream = null;
      try {
        Properties props = new Properties();
        stream = getClass().getResourceAsStream(path);
        if (stream != null) {
          props.load(stream);
          String contextName = props.getProperty("application.name");
          if (contextName != null && contextName.length() > 0)
            return contextName;
        }
      } catch (IOException io) {
        // ignore
      } finally {
        if (stream != null) {
          try {

            stream.close();
          } catch (IOException io) {
            // ignore
          }
        }
      }
    }
    StaticLoggerBinder loggerBinder = StaticLoggerBinder.getSingleton();
    LoggerContext loggerContext = (LoggerContext) loggerBinder.getLoggerFactory();
    return loggerContext.getName();
  }
}
