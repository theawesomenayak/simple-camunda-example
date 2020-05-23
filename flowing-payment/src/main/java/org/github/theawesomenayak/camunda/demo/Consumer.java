package org.github.theawesomenayak.camunda.demo;

import java.util.Set;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.api.ExternalWorker;

@Named
@AllArgsConstructor
public final class Consumer implements Runnable {

  private final Set<ExternalWorker> externalWorkers;

  @Override
  public void run() {

    externalWorkers.forEach(ExternalWorker::execute);
  }
}
