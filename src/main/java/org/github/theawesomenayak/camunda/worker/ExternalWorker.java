package org.github.theawesomenayak.camunda.worker;

import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

abstract class ExternalWorker {

  protected final ExternalTaskClient client;

  protected final ExternalTaskHandler externalTaskHandler;

  protected ExternalWorker(final ExternalTaskClient client,
      final ExternalTaskHandler externalTaskHandler) {

    this.client = client;
    this.externalTaskHandler = externalTaskHandler;
  }

  public abstract void execute();
}
