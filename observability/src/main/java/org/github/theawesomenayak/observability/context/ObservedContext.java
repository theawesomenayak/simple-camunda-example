package org.github.theawesomenayak.observability.context;

import com.google.common.base.Stopwatch;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Tracer;
import java.util.concurrent.TimeUnit;
import org.github.theawesomenayak.observability.common.Observed;

public final class ObservedContext {

  private final LogContext logContext;

  private final MetricContext metricContext;

  private final TraceContext traceContext;

  final Stopwatch stopwatch;

  public ObservedContext(final MeterRegistry meterRegistry, final Tracer tracer,
      final String name) {

    this.logContext = new LogContext();
    this.metricContext = new MetricContext(meterRegistry, name);
    this.traceContext = new TraceContext(tracer, name);
    this.stopwatch = Stopwatch.createStarted();
  }

  public void recordSuccess(final Observed observed) {

    logContext.logSuccess(observed);
    metricContext.recordSuccess(stopwatch.elapsed(TimeUnit.MILLISECONDS));
  }

  public void recordFailure(final Observed observed) {

    logContext.logFailure(observed);
    metricContext.recordFailure();
  }

  public void recordFinal() {

    traceContext.capture();
  }
}
