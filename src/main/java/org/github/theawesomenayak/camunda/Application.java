package org.github.theawesomenayak.camunda;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;
import org.github.theawesomenayak.camunda.rest.RestClient;
import org.github.theawesomenayak.camunda.rest.request.Variable;
import org.github.theawesomenayak.camunda.worker.ExternalWorker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

  private final RestClient restClient;

  private final Set<ExternalWorker> externalWorkers;

  @Inject
  public Application(final RestClient restClient, final Set<ExternalWorker> externalWorkers) {

    this.restClient = restClient;

    this.externalWorkers = externalWorkers;
  }

  public static void main(final String[] args) {

    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(final String... args) {

    setupProcess();
    runWorkers();
  }

  private void setupProcess() {

    restClient.createDeployment("Payment Retrieval", "payment.bpmn", "approve-payment.dmn");

    final ExecutorService executorService = Executors.newFixedThreadPool(500);

    for (int i = 0; i < 100; i++) {
      executorService.submit(() -> {
        final Map<String, Variable> variables = new HashMap<>();

        final long amount = ThreadLocalRandom.current().nextLong(100, 1000);
        variables.put("amount", Variable.getLong(amount));

        final String[] items = {"Phone", "Laptop", "Charger"};
        final String item = items[ThreadLocalRandom.current().nextInt(0, 2)];
        variables.put("item", Variable.getString(item));
        variables.put("receiver", Variable.getString("user@gmail.com"));
        variables.put("message", Variable.getString("Hello User!!Thank you for buying a " + item));
        restClient.startProcess("payment-retrieval", variables);
      });
    }
  }

  private void runWorkers() {

    externalWorkers.forEach(ExternalWorker::execute);
  }
}
