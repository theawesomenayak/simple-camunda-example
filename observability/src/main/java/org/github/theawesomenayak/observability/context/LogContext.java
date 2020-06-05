package org.github.theawesomenayak.observability.context;

import org.github.theawesomenayak.observability.common.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LogContext {

  private static final Logger LOG = LoggerFactory.getLogger(LogContext.class);

  public void logSuccess(final Observed observed) {

    LOG.info("{}", observed);
  }

  public void logFailure(final Observed observed) {

    LOG.error("{}", observed, observed.getException());
  }
}
