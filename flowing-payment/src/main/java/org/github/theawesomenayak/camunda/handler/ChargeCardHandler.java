package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.api.TaskHandler;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.service.PaymentService;

@AllArgsConstructor
@Named("charge.card.handler")
public final class ChargeCardHandler extends TaskHandler {

  private final PaymentService paymentService;

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final long amount = externalTask.getVariable("amount");
    paymentService.charge(PaymentInstrument.CREDIT_CARD, amount);
    externalTaskService.complete(externalTask);
  }
}