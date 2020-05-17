package org.github.theawesomenayak.camunda.worker;

import javax.inject.Inject;
import javax.inject.Named;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

@Named
public final class SendNotificationWorker extends ExternalWorker {

  private static final String TOPIC = "send-notification";

  @Inject
  public SendNotificationWorker(final ExternalTaskClient externalTaskClient,
    @Named("send.notification.handler") final ExternalTaskHandler externalTaskHandler) {

    super(TOPIC, externalTaskClient, externalTaskHandler);
  }
}