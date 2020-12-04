package com.bee42.messages;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.bee42.messages.exception.ErrorMessages;
import com.bee42.messages.exception.MessageException;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.slf4j.LoggerFactory;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MessageLoggingTest {

  @Test
  public void testFormat() {
    // setup
    Logger LOG = new Logger(this.getClass());

    Appender mockAppender = mock(Appender.class);
    ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory
        .getLogger(ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
    try {
      when(mockAppender.getName()).thenReturn("MOCK");
      root.addAppender(mockAppender);
      // execute
      LOG.error(ErrorMessages.NOT_FOUND_ERROR, "test");
      // verify
      verify(mockAppender).doAppend(argThat(new ArgumentMatcher<Object>() {
        @Override
        public boolean matches(final Object argument) {

          return ((LoggingEvent) argument).getArgumentArray().length == 1
              && ((LoggingEvent) argument).getFormattedMessage().contains(ErrorMessages.NOT_FOUND_ERROR.getCode());

        }
      }));
    } finally {
      root.detachAppender(mockAppender);
    }
    // How we can see real formatted String -- Construct a MemoryAppender with
    // Pattern
    LOG.error(new MessageException(ErrorMessages.GENERAL_ERROR));
    LOG.error(new MessageException(ErrorMessages.NOT_FOUND_ERROR, "test1"));
  }

}
