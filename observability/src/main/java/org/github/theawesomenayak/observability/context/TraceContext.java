package org.github.theawesomenayak.observability.context;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.Tracer.SpanBuilder;

public final class TraceContext {

  private final Span span;

  public TraceContext(final Tracer tracer, final String name) {

    final SpanBuilder builder = tracer.buildSpan(name);
    if (null != tracer.activeSpan()) {
      builder.asChildOf(tracer.activeSpan());
    }
    this.span = builder.start();
  }

  public void capture() {

    this.span.finish();
  }
}
