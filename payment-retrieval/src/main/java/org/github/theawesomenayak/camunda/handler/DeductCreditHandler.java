package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

@Slf4j
@Named("deduct.credit.handler")
public final class DeductCreditHandler extends TaskHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
    final ExternalTaskService externalTaskService) {

    final String item = externalTask.getVariable("item");
    final Long amount = externalTask.getVariable("amount");
    log.info("Deducting credit balance with an amount of €{} for the item {}...", amount, item);
    externalTaskService.complete(externalTask);
  }
}
