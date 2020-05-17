package org.github.theawesomenayak.camunda.config;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfiguration {

  @Bean
  ExternalTaskClient externalTaskClient() {

    return ExternalTaskClient.create()
      .baseUrl("http://localhost:8080/engine-rest")
      .asyncResponseTimeout(10000) // long polling timeout
      .build();
  }
}