package org.github.theawesomenayak.camunda.handler;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.api.TaskHandler;
import org.github.theawesomenayak.model.NotificationChannel;
import org.github.theawesomenayak.model.NotificationParams;
import org.github.theawesomenayak.service.NotificationService;

@AllArgsConstructor
@Named
public final class SendNotificationHandler extends TaskHandler {

  private final NotificationService notificationService;

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final boolean approved = BooleanUtils
        .toBooleanDefaultIfNull(externalTask.getVariable("approved"), true);
    notificationService.sendNotification(NotificationChannel.EMAIL,
        NotificationParams.builder()
            .receiver(externalTask.getVariable("receiver"))
            .message(approved
                ? externalTask.getVariable("approvedMessage")
                : externalTask.getVariable("rejectedMessage"))
            .build());
    externalTaskService.complete(externalTask);
  }
}
