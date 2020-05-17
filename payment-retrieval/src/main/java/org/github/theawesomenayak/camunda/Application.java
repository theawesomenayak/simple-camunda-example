package org.github.theawesomenayak.camunda;

import java.util.Set;
import javax.inject.Inject;
import org.github.theawesomenayak.camunda.worker.ExternalWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final Set<ExternalWorker> externalWorkers;

  @Inject
  public Application(final Set<ExternalWorker> externalWorkers) {

    this.externalWorkers = externalWorkers;
  }

  public static void main(final String[] args) {

    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(final String... args) {

    externalWorkers.forEach(ExternalWorker::execute);
  }
}
