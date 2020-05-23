package org.github.theawesomenayak;

import java.util.concurrent.ThreadLocalRandom;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ServiceUtils {

  @SneakyThrows
  public static void delay(final long millis) {

    Thread.sleep(millis);
  }

  @SneakyThrows
  public static void delay() {

    Thread.sleep(ThreadLocalRandom.current().nextLong(500, 1500));
  }
}
