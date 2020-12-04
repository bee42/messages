package com.bee42.messages;

import com.bee42.messages.exception.MessageException;
import org.slf4j.LoggerFactory;

public class Logger {

  private org.slf4j.Logger logger;

  public Logger(final Class clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public Logger(final String loggerName) {
    this.logger = LoggerFactory.getLogger(loggerName);
  }

  public Logger(final org.slf4j.Logger logger) {
    this.logger = logger;
  }

  public boolean isErrorEnabled() {
    return logger.isErrorEnabled();
  }

  public boolean isWarnEnabled() {
    return logger.isWarnEnabled();
  }

  public boolean isInfoEnabled() {
    return logger.isInfoEnabled();
  }

  public boolean isDebugEnabled() {
    return logger.isDebugEnabled();
  }

  public void error(final MessageException exception) {
    error(exception.getErrorMessage(), exception.getArgs(), exception);
  }

  public void error(final MessageType type) {
    logger.error(getLogMessage(type));
  }

  public void error(final MessageType type, final Throwable throwable) {
    logger.error(getLogMessage(type), throwable);
  }

  public void error(final MessageType type, final Object... args) {
    logger.error(getLogMessage(type), args);
  }

  public void warn(final MessageException exception) {
    warn(exception.getErrorMessage(), exception.getArgs(), exception);
  }

  public void warn(final MessageType type) {
    logger.warn(getLogMessage(type));
  }

  public void warn(final MessageType type, final Throwable throwable) {
    logger.warn(getLogMessage(type), throwable);
  }

  public void warn(final MessageType type, final Object... args) {
    logger.warn(getLogMessage(type), args);
  }

  public void info(final MessageException exception) {
    info(exception.getErrorMessage(), exception.getArgs(), exception);
  }

  public void info(final MessageType type) {
    logger.info(getLogMessage(type));
  }

  public void info(final MessageType type, final Throwable throwable) {
    logger.info(getLogMessage(type), throwable);
  }

  public void info(final MessageType type, final Object... args) {
    logger.info(getLogMessage(type), args);
  }

  public void debug(final MessageException exception) {
    debug(exception.getErrorMessage(), exception.getArgs(), exception);
  }

  public void debug(final MessageType type) {
    logger.debug(getLogMessage(type));
  }

  public void debug(final MessageType type, final Throwable throwable) {
    logger.debug(getLogMessage(type), throwable);
  }

  public void debug(final MessageType type, final Object... args) {
    logger.debug(getLogMessage(type), args);
  }

  public org.slf4j.Logger getLogger() {
    return logger;
  }

  protected String getLogMessage(final MessageType type) {
    return type.getCode() + ": " + type.getLogMessage();
  }
}
