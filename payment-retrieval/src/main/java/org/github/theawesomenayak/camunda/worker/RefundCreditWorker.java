package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class RefundCreditWorker extends ExternalWorker {

  private static final String TOPIC = "refund-credit";

  @Inject
  public RefundCreditWorker(final ExternalTaskClient externalTaskClient,
    @Named("refund.credit.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
