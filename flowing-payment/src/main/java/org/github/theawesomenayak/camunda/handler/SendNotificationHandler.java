package org.github.theawesomenayak.camunda.handler;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.APPROVED;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.APPROVED_MESSAGE;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.RECEIVER;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.REJECTED_MESSAGE;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;
import org.github.theawesomenayak.model.NotificationChannel;
import org.github.theawesomenayak.model.NotificationParams;
import org.github.theawesomenayak.observability.annotation.Observe;
import org.github.theawesomenayak.service.NotificationService;

@Named
@AllArgsConstructor
public class SendNotificationHandler extends ExternalHandler {

  private final NotificationService notificationService;

  @Observe
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    try {
      final boolean approved = BooleanUtils
          .toBooleanDefaultIfNull(externalTask.getVariable(APPROVED.key()), true);
      notificationService.sendNotification(NotificationChannel.EMAIL,
          NotificationParams.builder()
              .receiver(externalTask.getVariable(RECEIVER.key()))
              .message(approved
                  ? externalTask.getVariable(APPROVED_MESSAGE.key())
                  : externalTask.getVariable(REJECTED_MESSAGE.key()))
              .build());
      externalTaskService.complete(externalTask);
    } catch (final Exception e) {
      handleFailure(externalTask, externalTaskService, e);
    }
  }
}
