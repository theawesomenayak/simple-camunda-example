package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;
import org.github.theawesomenayak.observability.Observe;

@Named
@AllArgsConstructor
public class BookCabHandler extends ExternalHandler {

  @Observe
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    try {
    } catch (final Exception e) {
      handleFailure(externalTask, externalTaskService, e);
    }
  }
}