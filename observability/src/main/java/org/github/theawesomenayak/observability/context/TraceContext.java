package org.github.theawesomenayak.observability.context;

import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.github.theawesomenayak.observability.common.Observed;

public final class TraceContext {

  private final Span span;
  private final Scope scope;

  public TraceContext(final Tracer tracer, final Observed observed) {

    this.span = tracer.buildSpan(observed.getIdentifier()).start();
    this.scope = tracer.activateSpan(this.span);
  }

  public void capture() {

    this.scope.close();
    this.span.finish();
  }
}
