package org.github.theawesomenayak.service;

import org.github.theawesomenayak.model.NotificationChannel;
import org.github.theawesomenayak.model.NotificationParams;

public interface NotificationService {

  void sendNotification(NotificationChannel notificationChannel,
      NotificationParams notificationParams);
}
