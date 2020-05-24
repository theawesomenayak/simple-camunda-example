package org.github.theawesomenayak.camunda;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Status {

  STARTED("Started"),

  ERROR("Error"),

  COMPLETED("Completed");

  private final String value;
}
