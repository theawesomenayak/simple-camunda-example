package org.github.theawesomenayak.camunda.demo;

import javax.inject.Named;
import lombok.AllArgsConstructor;

@Named
@AllArgsConstructor
public final class Producer implements Runnable {

  private final PaymentRetrieval paymentRetrieval;

  @Override
  public void run() {

    paymentRetrieval.deploy();
    paymentRetrieval.startProcess(10);
  }
}
