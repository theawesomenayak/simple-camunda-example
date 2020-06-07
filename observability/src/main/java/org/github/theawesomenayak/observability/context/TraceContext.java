package org.github.theawesomenayak.observability.context;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;

public final class TraceContext {

  private final Span span;
  private final Scope scope;

  public TraceContext(final Tracer tracer, final String name) {

    this.span = tracer.buildSpan(name).start();
    this.scope = tracer.activateSpan(this.span);
  }

  public void capture() {

    this.scope.close();
    this.span.finish();
  }
}
