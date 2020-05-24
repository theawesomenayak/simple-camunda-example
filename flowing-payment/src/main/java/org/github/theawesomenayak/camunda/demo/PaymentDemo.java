package org.github.theawesomenayak.camunda.demo;

import javax.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public final class PaymentDemo implements Runnable {

  private final Producer producer;

  private final Consumer consumer;

  @Override
  public void run() {

    consumer.run();
    producer.run();
  }
}
