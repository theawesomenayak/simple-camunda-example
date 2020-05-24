package org.github.theawesomenayak.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.github.theawesomenayak")
public class WorkerService {

  public static void main(final String[] args) {

    SpringApplication.run(WorkerService.class, args);
  }
}
