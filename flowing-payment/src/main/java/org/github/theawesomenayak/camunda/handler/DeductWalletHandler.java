package org.github.theawesomenayak.camunda.handler;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.AMOUNT;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.service.PaymentService;

@AllArgsConstructor
@Named
public final class DeductWalletHandler extends ExternalHandler {

  private final PaymentService paymentService;

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final long amount = externalTask.getVariable(AMOUNT.key());
    paymentService.charge(PaymentInstrument.WALLET, amount);
    externalTaskService.complete(externalTask);
  }
}
