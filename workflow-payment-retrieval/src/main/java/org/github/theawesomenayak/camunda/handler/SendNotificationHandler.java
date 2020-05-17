package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.model.NotificationChannel;
import org.github.theawesomenayak.model.NotificationParams;
import org.github.theawesomenayak.service.NotificationService;

@AllArgsConstructor
@Named("send.notification.handler")
public final class SendNotificationHandler extends TaskHandler {

  private final NotificationService notificationService;

  @Override
  protected void handle(final ExternalTask externalTask,
    final ExternalTaskService externalTaskService) {

    notificationService.sendNotification(NotificationChannel.EMAIL,
      NotificationParams.builder()
        .receiver(externalTask.getVariable("receiver"))
        .message(externalTask.getVariable("message"))
        .build());
    externalTaskService.complete(externalTask);
  }
}
