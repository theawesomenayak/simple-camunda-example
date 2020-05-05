package org.github.theawesomenayak.camunda.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

public abstract class ExternalWorker {

  private final String topic;

  protected final ExternalTaskClient client;

  protected final ExternalTaskHandler externalTaskHandler;

  private final ExecutorService executorService;

  protected ExternalWorker(final String topic, final ExternalTaskClient client,
      final ExternalTaskHandler externalTaskHandler) {

    this.topic = topic;
    this.client = client;
    this.externalTaskHandler = externalTaskHandler;
    this.executorService = Executors.newFixedThreadPool(10);
  }

  public final void execute() {

    executorService.submit(() -> client.subscribe(topic)
        .lockDuration(1000)
        .handler(externalTaskHandler)
        .open());
  }
}
