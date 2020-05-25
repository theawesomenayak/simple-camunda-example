package org.github.theawesomenayak.camunda.client.variable;

import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class VariableMap {

  private final Map<String, Variable> variables = new HashMap<>();

  public VariableMap putValue(final VariableName variableName, final long value) {

    return put(variableName.name(), new Variable(value, "Long"));
  }

  public VariableMap putValue(final VariableName variableName, final double value) {

    return put(variableName.name(), new Variable(value, "Double"));
  }

  public VariableMap putValue(final VariableName variableName, final String value) {

    return put(variableName.name(), new Variable(value, "String"));
  }

  public Map<String, Variable> getVariables() {

    return ImmutableMap.copyOf(variables);
  }

  private VariableMap put(final String key, final Variable variable) {

    this.variables.put(key, variable);
    return this;
  }
}
