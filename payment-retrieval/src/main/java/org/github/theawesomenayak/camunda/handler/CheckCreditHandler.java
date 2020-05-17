package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

@Slf4j
@Named("check.credit.handler")
public final class CheckCreditHandler extends TaskHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
    final ExternalTaskService externalTaskService) {

    final String customerId = externalTask.getVariable("customerId");
    log.info("Checking credit balance for customer {}...", customerId);
    externalTaskService.complete(externalTask);
  }
}
