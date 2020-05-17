package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class CheckCreditWorker extends ExternalWorker {

  private static final String TOPIC = "check-credit";

  @Inject
  public CheckCreditWorker(final ExternalTaskClient externalTaskClient,
    @Named("check.credit.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
