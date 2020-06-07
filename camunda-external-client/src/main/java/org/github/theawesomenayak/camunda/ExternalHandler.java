package org.github.theawesomenayak.camunda;

import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;

public abstract class ExternalHandler implements ExternalTaskHandler {

  protected void handleFailure(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService, final Exception e) {

    externalTaskService.handleFailure(externalTask, e.getClass().getSimpleName(), e.getMessage(),
        externalTask.getRetries() - 1, 500);
  }
}
