package org.github.theawesomenayak.camunda.client.request;

import lombok.Getter;

@Getter
public final class Variable {

  private final Object value;

  private final String type;

  Variable(final Object value, final String type) {

    this.value = value;
    this.type = type;
  }

  public static Variable getLong(final long value) {

    return new Variable(value, "Long");
  }

  public static Variable getString(final String value) {

    return new Variable(value, "String");
  }
}
