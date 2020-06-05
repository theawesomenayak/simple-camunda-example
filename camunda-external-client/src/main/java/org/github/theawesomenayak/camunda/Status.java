package org.github.theawesomenayak.camunda;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

  STARTED("Started"),

  ERROR("Error"),

  COMPLETED("Completed");

  private final String value;
}
