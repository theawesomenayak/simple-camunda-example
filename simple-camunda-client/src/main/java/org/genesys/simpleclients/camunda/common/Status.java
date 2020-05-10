package org.genesys.simpleclients.camunda.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {

  STARTED("Started"),

  ERROR("Error"),

  COMPLETED("Completed");

  private final String value;
}
