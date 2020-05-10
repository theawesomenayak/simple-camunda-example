package org.genesys.simpleclients.camunda.worker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;

public abstract class ExternalWorker {

  final ExternalTaskClient client;

  final ExternalTaskHandler externalTaskHandler;

  private final String topic;

  private final ExecutorService executorService;

  protected ExternalWorker(final String topic, final ExternalTaskClient client,
    final ExternalTaskHandler externalTaskHandler, final int numberOfThreads) {

    this.topic = topic;
    this.client = client;
    this.externalTaskHandler = externalTaskHandler;
    this.executorService = Executors.newFixedThreadPool(numberOfThreads);
  }

  public final void execute() {

    executorService.submit(() -> client.subscribe(topic)
      .lockDuration(1000)
      .handler(externalTaskHandler)
      .open());
  }
}
