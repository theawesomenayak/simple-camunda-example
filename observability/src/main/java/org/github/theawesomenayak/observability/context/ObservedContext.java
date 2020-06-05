package org.github.theawesomenayak.observability.context;

import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Tracer;
import org.github.theawesomenayak.observability.common.Observed;

public final class ObservedContext {

  private final LogContext logContext;

  private final MetricContext metricContext;

  private final TraceContext traceContext;

  public ObservedContext(final MeterRegistry meterRegistry, final Tracer tracer,
      final String name) {

    this.logContext = new LogContext();
    this.metricContext = new MetricContext(meterRegistry, name);
    this.traceContext = new TraceContext(tracer, name);
  }

  public void recordSuccess(final Observed observed, final long duration) {

    logContext.logSuccess(observed);
    metricContext.recordSuccess(duration);
  }

  public void recordFailure(final Observed observed) {

    logContext.logFailure(observed);
    metricContext.recordFailure();
  }

  public void recordFinal() {

    traceContext.capture();
  }
}
