package org.github.theawesomenayak.camunda.config;

import org.github.theawesomenayak.camunda.Topic;

public enum Topics implements Topic {

  APPROVE_PAYMENT,
  CHARGE_CARD,
  CHECK_WALLET,
  DEDUCT_WALLET,
  REFUND_WALLET,
  SEND_NOTIFICATION
}
