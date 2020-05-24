package org.github.theawesomenayak.camunda.handler;

import com.google.common.collect.ImmutableMap;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.api.TaskHandler;
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

    final long amount = externalTask.getVariable("amount");
    final String userId = externalTask.getVariable("userId");
    final long balance = paymentService.checkBalance(userId, PaymentInstrument.WALLET);
    log.info("UserId={} Balance={}", userId, balance);
    final boolean hasBalance = Double.compare(amount, balance) < 0;
    externalTaskService.complete(externalTask, ImmutableMap.of("hasBalance", hasBalance));
  }
}
