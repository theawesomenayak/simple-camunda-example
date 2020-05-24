package org.github.theawesomenayak.camunda.client.variable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public final class Variable {

  private final Object value;

  private final String type;
}
