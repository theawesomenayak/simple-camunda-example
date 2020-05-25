package org.github.theawesomenayak.camunda.config;

import lombok.AllArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.github.theawesomenayak.camunda.ExternalWorker;
import org.github.theawesomenayak.camunda.handler.BookCabHandler;
import org.github.theawesomenayak.camunda.handler.BookFlightHandler;
import org.github.theawesomenayak.camunda.handler.BookHotelHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WorkerConfiguration {

  private static final int NUMBER_OF_THREADS = 1;

  private final ExternalTaskClient externalTaskClient;

  @Bean
  ExternalWorker bookFlightWorker(final BookFlightHandler externalTaskHandler) {

    return new ExternalWorker("book-flight", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker bookHotelWorker(final BookHotelHandler externalTaskHandler) {

    return new ExternalWorker("book-hotel", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker bookCabWorker(final BookCabHandler externalTaskHandler) {

    return new ExternalWorker("book-cab", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }
}
