package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;

@Named
@AllArgsConstructor
public final class BookFlightHandler extends ExternalHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

  }
}
