package com.bee42.messages;

/**
 * Simple tupple class to document parameters in Message definitions.
 */
public class Parameter {
  private String name;

  private String description;

  /**
   * Creates a new instance.
   *
   * @param name        of the placeholder
   * @param description of the placeholder
   */
  public Parameter(final String name, final String description) {

    this.name = name;
    this.description = description;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {

    return name;

  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription() {

    return description;
  }
}
