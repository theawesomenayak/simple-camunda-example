package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class RefundWalletWorker extends ExternalWorker {

  private static final String TOPIC = "refund-wallet";

  @Inject
  public RefundWalletWorker(final ExternalTaskClient externalTaskClient,
    @Named("refund.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}
