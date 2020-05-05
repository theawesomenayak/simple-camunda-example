package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.common.Status;

@Slf4j
@Named
public final class ChargeCardHandler implements ExternalTaskHandler {

  private static final String LOG_FORMAT = "TaskId={} Status={}";

  @SneakyThrows
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    log.info(LOG_FORMAT, externalTask.getId(), Status.STARTED);
    try {
      final String item = externalTask.getVariable("item");
      final Long amount = externalTask.getVariable("amount");
      log.info("Charging credit card with an amount of €{} for the item {}...", amount, item);
      externalTaskService.complete(externalTask);
      log.info(LOG_FORMAT, externalTask.getId(), Status.COMPLETED);
    } catch (final Exception e) {
      externalTaskService.handleBpmnError(externalTask, e.getMessage());
      log.error(LOG_FORMAT, externalTask.getId(), Status.ERROR, e);
    }
  }
}