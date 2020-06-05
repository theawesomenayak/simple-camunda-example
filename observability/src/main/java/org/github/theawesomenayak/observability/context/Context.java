package org.github.theawesomenayak.observability.context;

import java.util.Map;
import org.slf4j.MDC;

public final class Context {

  public void add(final String key, final String value) {

    MDC.put(key, value);
  }

  public void add(final Map<String, String> map) {

    map.forEach(MDC::put);
  }

  public String get(final String key) {

    return MDC.get(key);
  }

  public Map<String, String> get() {

    return MDC.getCopyOfContextMap();
  }
}
