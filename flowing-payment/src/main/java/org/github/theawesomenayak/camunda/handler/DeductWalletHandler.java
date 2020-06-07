package org.github.theawesomenayak.camunda.handler;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.AMOUNT;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.observability.Observe;
import org.github.theawesomenayak.service.PaymentService;

@Named
@AllArgsConstructor
public class DeductWalletHandler extends ExternalHandler {

  private final PaymentService paymentService;

  @Observe
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    try {
      final long amount = externalTask.getVariable(AMOUNT.key());
      paymentService.charge(PaymentInstrument.WALLET, amount);
      externalTaskService.complete(externalTask);
    } catch (final Exception e) {
      handleFailure(externalTask, externalTaskService, e);
    }
  }
}
