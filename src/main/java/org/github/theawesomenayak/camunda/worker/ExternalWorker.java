package org.github.theawesomenayak.camunda.worker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

public abstract class ExternalWorker {

  private final String topic;

  protected final ExternalTaskClient client;

  protected final ExternalTaskHandler externalTaskHandler;

  protected ExternalWorker(final String topic, final ExternalTaskClient client,
      final ExternalTaskHandler externalTaskHandler) {

    this.topic = topic;
    this.client = client;
    this.externalTaskHandler = externalTaskHandler;
  }

  public final void execute() {

    client.subscribe(topic)
        .lockDuration(1000)
        .handler(externalTaskHandler)
        .open();
  }
}
