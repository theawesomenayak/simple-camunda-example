package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class CheckWalletWorker extends ExternalWorker {

  private static final String TOPIC = "check-wallet";

  @Inject
  public CheckWalletWorker(final ExternalTaskClient externalTaskClient,
    @Named("check.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
