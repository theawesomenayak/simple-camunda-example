package org.github.theawesomenayak.observability.common;

import lombok.Builder;
import lombok.Getter;
import org.github.theawesomenayak.observability.context.Context;

@Getter
@Builder
public final class Observed {

  private final String identifier;

  private final Result result;

  private final Throwable exception;

  private final Context context;

  @Override
  public String toString() {

    return "identifier=" + identifier +
        ", result=" + result +
        ", exception=" + exception +
        ", context=" + context;
  }
}
