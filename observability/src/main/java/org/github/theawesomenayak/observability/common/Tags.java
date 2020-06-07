package org.github.theawesomenayak.observability.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tags {

  IDENTIFIER("identifier");

  private final String value;
}
