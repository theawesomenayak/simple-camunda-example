package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class DeductWalletWorker extends ExternalWorker {

  private static final String TOPIC = "deduct-wallet";

  @Inject
  public DeductWalletWorker(final ExternalTaskClient externalTaskClient,
    @Named("deduct.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
