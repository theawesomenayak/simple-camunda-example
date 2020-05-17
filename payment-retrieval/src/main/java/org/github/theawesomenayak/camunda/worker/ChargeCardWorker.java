package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class ChargeCardWorker extends ExternalWorker {

  private static final String TOPIC = "charge-card";

  @Inject
  public ChargeCardWorker(final ExternalTaskClient externalTaskClient,
    @Named("charge.card.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}