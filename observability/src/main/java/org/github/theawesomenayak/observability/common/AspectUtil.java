package org.github.theawesomenayak.observability.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AspectUtil {

  public static String getIdentifier(final ProceedingJoinPoint pjp) {

    final Signature signature = pjp.getSignature();
    return String.format("%s.%s",
        signature.getDeclaringType().getSimpleName(),
        signature.getName());
  }

  public static String getClassName(final ProceedingJoinPoint pjp) {

    return pjp.getSignature().getDeclaringType().getSimpleName();
  }

  public static String getMethodName(final ProceedingJoinPoint pjp) {

    return pjp.getSignature().getName();
  }
}
