package org.github.theawesomenayak.camunda.config;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.camunda.bpm.client.ExternalTaskClient;
import org.genesys.simpleclients.camunda.worker.ExternalWorker;
import org.github.theawesomenayak.camunda.registry.WorkerRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "org.genesys.simpleclients")
@Configuration
public class BeanConfiguration {

  @Bean
  ExternalTaskClient externalTaskClient() {

    return ExternalTaskClient.create()
      .baseUrl("http://localhost:8080/engine-rest")
      .asyncResponseTimeout(10000) // long polling timeout
      .build();
  }

  @Bean
  Set<ExternalWorker> externalWorkers(final ApplicationContext context) {

    return Stream.of(WorkerRegistry.values())
      .map(value -> context.getBean(value.getWorkerClass()))
      .collect(Collectors.toSet());
  }
}