package org.github.theawesomenayak.camunda.config;

import static org.github.theawesomenayak.camunda.common.Constants.CAMUNDA_BASE_URL;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfiguration {

  @Bean
  ExternalTaskClient externalTaskClient() {

    return ExternalTaskClient.create()
      .baseUrl(CAMUNDA_BASE_URL)
      .asyncResponseTimeout(10000) // long polling timeout
      .build();
  }
}