package org.github.theawesomenayak.observability.context;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class MetricContext {

  private final Counter totalCounter;

  private final Counter errorCounter;

  private final Timer timer;

  private final AtomicLong gauge;

  public MetricContext(final MeterRegistry meterRegistry, final String name) {

    this.totalCounter = meterRegistry.counter(name + ".total");
    this.errorCounter = meterRegistry.counter(name + ".error");
    this.timer = meterRegistry.timer(name + ".time");
    this.gauge = meterRegistry.gauge(name + ".value", new AtomicLong(0));
  }

  public void recordSuccess(final long duration) {

    this.totalCounter.increment();
    this.timer.record(Duration.of(duration, ChronoUnit.MILLIS));
  }

  public void recordFailure() {

    this.totalCounter.increment();
    this.errorCounter.increment();
  }

  public void measure(final long value) {

    this.gauge.set(value);
  }
}
