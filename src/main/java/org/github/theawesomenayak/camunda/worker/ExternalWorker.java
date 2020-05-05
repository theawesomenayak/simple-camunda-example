package org.github.theawesomenayak.camunda.worker;

import org.camunda.bpm.client.ExternalTaskClient;

public abstract class ExternalWorker {

  protected final ExternalTaskClient client;

  public ExternalWorker(final ExternalTaskClient client) {

    this.client = client;
  }

  public abstract void execute();
}
