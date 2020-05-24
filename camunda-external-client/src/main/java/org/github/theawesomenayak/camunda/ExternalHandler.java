package org.github.theawesomenayak.camunda;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;

@Slf4j
public abstract class ExternalHandler implements ExternalTaskHandler {

  private static final String LOG_FORMAT = "ProcessInstanceId={} TaskId={} Status={}";

  @Override
  public final void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    log.debug(LOG_FORMAT, externalTask.getProcessInstanceId(), externalTask.getId(),
        Status.STARTED);
    try {
      handle(externalTask, externalTaskService);
      log.debug(LOG_FORMAT, externalTask.getProcessInstanceId(), externalTask.getId(),
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
