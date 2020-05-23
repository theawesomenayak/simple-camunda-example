package org.github.theawesomenayak.camunda.client.variable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Variables {

  public static VariableMap createVariables() {

    return new VariableMap();
  }
}
