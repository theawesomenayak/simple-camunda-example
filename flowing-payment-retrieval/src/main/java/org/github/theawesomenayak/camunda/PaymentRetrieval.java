package org.github.theawesomenayak.camunda;

import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.demo.Consumer;
import org.github.theawesomenayak.camunda.demo.Producer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication(scanBasePackages = "org.github.theawesomenayak")
public class PaymentRetrieval implements CommandLineRunner {

  private final Producer producer;

  private final Consumer consumer;

  public static void main(final String[] args) {

    SpringApplication.run(PaymentRetrieval.class, args);
  }

  @Override
  public void run(final String... args) {

    consumer.run();
    producer.run();
  }
}
