package org.github.theawesomenayak.observability;

import com.google.common.base.Stopwatch;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Tracer;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.github.theawesomenayak.observability.common.AspectUtil;
import org.github.theawesomenayak.observability.common.Observed;
import org.github.theawesomenayak.observability.common.Result;
import org.github.theawesomenayak.observability.context.ObservedContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@Aspect
@AllArgsConstructor
public class ObserveAspect {

  private static final Logger LOGGER = LoggerFactory.getLogger(ObserveAspect.class);

  private final MeterRegistry meterRegistry;

  private final Tracer tracer;

  @Pointcut("@annotation(observe)")
  public void annotated(final Observe observe) {
    // defines all annotated objects
  }

  @Around("annotated(observe)")
  public Object around(final ProceedingJoinPoint pjp, final Observe observe) throws Throwable {

    final String identifier = StringUtils
        .defaultIfBlank(observe.identifier(), AspectUtil.getIdentifier(pjp));
    final Observed.ObservedBuilder builder = Observed.builder()
        .identifier(identifier);
    final ObservedContext observedContext = new ObservedContext(meterRegistry, tracer, identifier);
    final Stopwatch stopwatch = Stopwatch.createStarted();
    try {
      final Object value = pjp.proceed();
      observedContext.recordSuccess(builder.result(Result.SUCCESSFUL).build(),
          stopwatch.elapsed(TimeUnit.MILLISECONDS));
      return value;
    } catch (final Exception ex) {
      observedContext.recordFailure(builder.result(Result.FAILED).exception(ex).build());
      throw ex;
    } finally {
      observedContext.recordFinal();
    }
  }
}
