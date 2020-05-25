package org.github.theawesomenayak.camunda.demo;

import java.util.Set;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.ExternalWorker;

@Named
@AllArgsConstructor
public final class PaymentDemo implements Runnable {

  private final PaymentRetrieval paymentRetrieval;

  private final Set<ExternalWorker> externalWorkers;

  @Override
  public void run() {

    externalWorkers.forEach(ExternalWorker::execute);
    paymentRetrieval.deploy();
    paymentRetrieval.startProcess(10);
  }
}
