package org.github.theawesomenayak.camunda;

import javax.inject.Inject;
import org.github.theawesomenayak.camunda.worker.ChargeCardWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final ChargeCardWorker chargeCardWorker;

  @Inject
  public Application(final ChargeCardWorker chargeCardWorker) {

    this.chargeCardWorker = chargeCardWorker;
  }

  public static void main(final String[] args) {

    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(final String... args) {

    chargeCardWorker.execute();
  }
}
