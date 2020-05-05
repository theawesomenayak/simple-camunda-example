package org.github.theawesomenayak.camunda.handler;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.common.Status;

@Slf4j
abstract class TaskHandler implements ExternalTaskHandler {

  protected static final String LOG_FORMAT = "ProcessInstanceId={} TaskId={} Status={}";

  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    log.info(LOG_FORMAT, externalTask.getProcessInstanceId(), externalTask.getId(), Status.STARTED);
    try {
      handle(externalTask, externalTaskService);
      log.info(LOG_FORMAT, externalTask.getProcessInstanceId(), externalTask.getId(),
          Status.COMPLETED);
    } catch (final Exception e) {
      externalTaskService.handleBpmnError(externalTask, e.getMessage());
      log.error(LOG_FORMAT, externalTask.getProcessInstanceId(), externalTask.getId(), Status.ERROR,
          e);
    }
  }

  protected abstract void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService);
}
