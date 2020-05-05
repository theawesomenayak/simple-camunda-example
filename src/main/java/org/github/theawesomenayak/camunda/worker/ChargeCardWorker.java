package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Slf4j
@Named
public class ChargeCardWorker extends ExternalWorker {

  private final ExternalTaskHandler externalTaskHandler;

  @Inject
  public ChargeCardWorker(final ExternalTaskClient externalTaskClient,
      final ExternalTaskHandler externalTaskHandler) {

    super(externalTaskClient);
    this.externalTaskHandler = externalTaskHandler;
  }

  @Override
  public void execute() {
    // subscribe to an external task topic as specified in the process
    client.subscribe("charge-card")
        .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
        .handler(externalTaskHandler)
        .open();
  }
}