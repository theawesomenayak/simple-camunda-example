package org.github.theawesomenayak.camunda;

import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfiguration {

  public static final String CAMUNDA_BASE_URL = "http://localhost:8080/engine-rest";

  @Bean
  ExternalTaskClient externalTaskClient() {

    return ExternalTaskClient.create()
        .baseUrl(CAMUNDA_BASE_URL)
        .asyncResponseTimeout(10000) // long polling timeout
        .build();
  }
}