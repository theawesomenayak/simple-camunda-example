package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.service.PaymentService;

@Slf4j
@AllArgsConstructor
@Named("check.wallet.handler")
public final class CheckWalletHandler extends TaskHandler {

  private final PaymentService paymentService;

  @Override
  protected void handle(final ExternalTask externalTask,
    final ExternalTaskService externalTaskService) {

    final String customerId = externalTask.getVariable("customerId");
    paymentService.checkBalance(customerId, PaymentInstrument.WALLET);
    externalTaskService.complete(externalTask);
  }
}
