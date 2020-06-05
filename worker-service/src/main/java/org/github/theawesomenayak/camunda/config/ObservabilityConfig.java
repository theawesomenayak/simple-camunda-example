package org.github.theawesomenayak.camunda.config;

import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.opentracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservabilityConfig {

  @Bean
  MeterRegistry meterRegistry() {

    return new CompositeMeterRegistry();
  }

  @Bean
  Tracer tracer() {

    final SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv()
        .withType("const")
        .withParam(1);
    final ReporterConfiguration reporterConfig = ReporterConfiguration.fromEnv().withLogSpans(true);
    final io.jaegertracing.Configuration config = new io.jaegertracing.Configuration(
        "worker-service")
        .withSampler(samplerConfig)
        .withReporter(reporterConfig);
    return config.getTracer();
  }
}
