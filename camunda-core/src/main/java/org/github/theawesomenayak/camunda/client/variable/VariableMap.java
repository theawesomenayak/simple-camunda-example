package org.github.theawesomenayak.camunda.client.variable;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class VariableMap {

  private final Map<String, Variable> variables = new HashMap<>();

  public VariableMap putValue(final String name, final long value) {

    return put(name, new Variable(value, "Long"));
  }

  public VariableMap putValue(final String name, final String value) {

    return put(name, new Variable(value, "String"));
  }

  public Map<String, Variable> getVariables() {

    return ImmutableMap.copyOf(variables);
  }

  private VariableMap put(final String key, final Variable variable) {

    this.variables.put(key, variable);
    return this;
  }
}
