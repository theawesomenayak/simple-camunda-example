package org.github.theawesomenayak.camunda.config;

import org.github.theawesomenayak.camunda.client.variable.VariableName;

public enum ProcessVariables implements VariableName {

  CUSTOMER_ID,
  ITEM,
  AMOUNT,
  APPROVED,
  HAS_BALANCE,
  RECEIVER,
  APPROVED_MESSAGE,
  REJECTED_MESSAGE;

  @Override
  public String key() {

    return this.name().toLowerCase();
  }
}
