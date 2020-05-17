package org.github.theawesomenayak.camunda.workflows;

import java.util.Set;
import javax.inject.Inject;
import org.github.theawesomenayak.camunda.worker.ExternalWorker;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public final class PaymentRetrievalTest {

  @Inject
  private PaymentRetrieval paymentRetrieval;

  @Inject
  private Set<ExternalWorker> externalWorkers;

  @Test
  public void testPaymentRetrieval() throws InterruptedException {

    paymentRetrieval.deploy();
    paymentRetrieval.startProcess(10);
    externalWorkers.forEach(ExternalWorker::execute);

    Thread.sleep(10000);
  }
}