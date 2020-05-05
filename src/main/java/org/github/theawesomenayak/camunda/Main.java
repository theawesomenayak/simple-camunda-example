package org.github.theawesomenayak.camunda;

import javax.inject.Inject;
import org.github.theawesomenayak.camunda.worker.ChargeCardWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

  private final ChargeCardWorker chargeCardWorker;

  @Inject
  public Main(final ChargeCardWorker chargeCardWorker) {

    this.chargeCardWorker = chargeCardWorker;
  }

  public static void main(final String[] args) {

    SpringApplication.run(Main.class, args);
  }

  @Override
  public void run(final String... args) throws Exception {

    chargeCardWorker.execute();
  }
}
