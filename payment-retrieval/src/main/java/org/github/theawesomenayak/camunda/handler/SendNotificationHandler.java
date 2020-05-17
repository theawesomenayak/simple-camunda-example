package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;

@Slf4j
@Named("notification.handler")
public final class SendNotificationHandler extends TaskHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
    final ExternalTaskService externalTaskService) {

    final String receiver = externalTask.getVariable("receiver");
    final String message = externalTask.getVariable("message");
    log.info("Sending Notification to {} with Message {}", receiver, message);
    externalTaskService.complete(externalTask);
  }
}
