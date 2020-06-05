package org.github.theawesomenayak.service.impl;

import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.model.NotificationChannel;
import org.github.theawesomenayak.model.NotificationParams;
import org.github.theawesomenayak.observability.Observe;
import org.github.theawesomenayak.service.NotificationService;

@Slf4j
@Named
public class NotificationServiceImpl implements NotificationService {

  @Observe
  @Override
  public void sendNotification(final NotificationChannel notificationChannel,
      final NotificationParams notificationParams) {

    log.info("Sending {} to {} with Message {}", notificationChannel.name(),
        notificationParams.getReceiver(), notificationParams.getMessage());
  }
}
