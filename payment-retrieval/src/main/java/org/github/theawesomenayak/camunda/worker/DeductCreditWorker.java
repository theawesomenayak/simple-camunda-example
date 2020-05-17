package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class DeductCreditWorker extends ExternalWorker {

  private static final String TOPIC = "deduct-credit";

  @Inject
  public DeductCreditWorker(final ExternalTaskClient externalTaskClient,
    @Named("deduct.credit.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
