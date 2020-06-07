package org.github.theawesomenayak.camunda.handler;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.AMOUNT;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.CUSTOMER_ID;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.HAS_BALANCE;

import com.google.common.collect.ImmutableMap;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.observability.Observe;
import org.github.theawesomenayak.service.PaymentService;

@Slf4j
@Named
@AllArgsConstructor
public class CheckWalletHandler extends ExternalHandler {

  private final PaymentService paymentService;

  @Observe
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    try {
      final long amount = externalTask.getVariable(AMOUNT.key());
      final String customerId = externalTask.getVariable(CUSTOMER_ID.key());
      final long balance = paymentService.checkBalance(customerId, PaymentInstrument.WALLET);
      log.info("CustomerId={} Balance={}", customerId, balance);
      final boolean hasBalance = Double.compare(amount, balance) < 0;
      externalTaskService.complete(externalTask, ImmutableMap.of(HAS_BALANCE.key(), hasBalance));
    } catch (final Exception e) {
      handleFailure(externalTask, externalTaskService, e);
    }
  }
}
