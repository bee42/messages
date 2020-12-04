package com.bee42.messages.exception;

import com.bee42.messages.MessageType;
import com.bee42.messages.Parameter;

public enum ErrorMessages implements MessageType {

  GENERAL_ERROR("SYSTEM_001", "A general error occurred.", "Nicht weiter spezifizierter technischer Fehler."),
  NOT_FOUND_ERROR("SYSTEM_002", "The requested resource {} does not exist.",
      "Diese Fehlermeldung wird ausgegeben, wenn versucht wird auf eine Resource zuzugreifen,"
          + " die nicht existiert.",
      new Parameter[] { new Parameter("resource", "Name der Ressource") });

  private String code;

  private String text;

  private String description;

  private Parameter[] parameters = new Parameter[0];

  private String textWithPlaceholders;

  /**
   * Constructs a new ErrorMessage.
   *
   * @param code an error code
   * @param text the error message
   */
  ErrorMessages(final String code, final String text) {

    this(code, text, null);
  }

  /**
   * Constructs a new ErrorMessage.
   *
   * @param code        the error code
   * @param text        the error message
   * @param description the error message description (only for documentation)
   */
  ErrorMessages(final String code, final String text, final String description, Parameter... parameters) {

    // Values used for Logging.
    this.code = code;
    this.text = text.replaceAll("\\{[^\\}.]*\\}", "{}");

    // Values used to generate documentation.
    this.textWithPlaceholders = text;
    this.description = description;
    this.parameters = parameters;

  }

  @Override
  public String getCode() {

    return code;
  }

  @Override
  public String getLogMessage() {

    return text;
  }

  public String getErrorLogMessage() {

    return code + ": " + text;
  }

  @Override
  public String getDescription() {

    return description;
  }

  /**
   * Returns the parameter list. Only used to create the documentation.
   *
   * @return never empty
   */
  public Parameter[] getParameters() {

    return parameters;
  }

  /**
   * Returns the origin text with placeholders like {field} and not the #text with
   * slf4j placeholders like {}.
   *
   * @return the text with non slf4j placeholders.
   */
  public String getTextWithPlaceholders() {

    return textWithPlaceholders;
  }

}