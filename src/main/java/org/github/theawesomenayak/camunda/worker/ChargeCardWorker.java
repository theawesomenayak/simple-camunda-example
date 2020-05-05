package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Slf4j
@Named
public final class ChargeCardWorker extends ExternalWorker {

  private static final String TOPIC = "charge-card";

  @Inject
  public ChargeCardWorker(final ExternalTaskClient externalTaskClient,
      final ExternalTaskHandler externalTaskHandler) {

    super(externalTaskClient, externalTaskHandler);
  }

  @Override
  public void execute() {

    client.subscribe(TOPIC)
        .lockDuration(1000)
        .handler(externalTaskHandler)
        .open();
  }
}