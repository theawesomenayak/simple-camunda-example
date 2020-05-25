package org.github.theawesomenayak.camunda.config;

import static org.github.theawesomenayak.camunda.config.Topics.BOOK_CAB;
import static org.github.theawesomenayak.camunda.config.Topics.BOOK_FLIGHT;
import static org.github.theawesomenayak.camunda.config.Topics.BOOK_HOTEL;

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

  private final ExternalTaskClient taskClient;

  @Bean
  ExternalWorker bookFlightWorker(final BookFlightHandler taskHandler) {

    return new ExternalWorker(BOOK_FLIGHT, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker bookHotelWorker(final BookHotelHandler taskHandler) {

    return new ExternalWorker(BOOK_HOTEL, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker bookCabWorker(final BookCabHandler taskHandler) {

    return new ExternalWorker(BOOK_CAB, taskClient, taskHandler, NUMBER_OF_THREADS);
  }
}
