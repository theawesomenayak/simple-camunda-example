package org.github.theawesomenayak.camunda.workflows;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;
import javax.inject.Named;
import org.github.theawesomenayak.camunda.rest.RestClient;
import org.github.theawesomenayak.camunda.rest.request.Variable;

@Named
public final class PaymentRetrieval implements Workflow {

  private final RestClient restClient;

  @Inject
  public PaymentRetrieval(final RestClient restClient) {

    this.restClient = restClient;
  }

  @Override
  public void deploy() {
    restClient.createDeployment("Payment Retrieval",
      "templates/payment.bpmn", "templates/approve-payment.dmn");
  }

  @Override
  public void startProcess(final int numberOfProcesses) {
    final ExecutorService executorService = Executors.newFixedThreadPool(numberOfProcesses);

    for (int i = 0; i < numberOfProcesses; i++) {
      executorService.submit(() -> {
        final Map<String, Variable> variables = getProcessVariables();
        restClient.startProcess("payment-retrieval", variables);
      });
    }
  }

  private Map<String, Variable> getProcessVariables() {
    final Map<String, Variable> variables = new HashMap<>();
    variables.put("userId", Variable.getLong(ThreadLocalRandom.current().nextLong()));

    final long amount = ThreadLocalRandom.current().nextLong(100, 1000);
    variables.put("amount", Variable.getLong(amount));

    final String[] items = {"Phone", "Laptop", "Charger"};
    final String item = items[ThreadLocalRandom.current().nextInt(0, items.length)];
    variables.put("item", Variable.getString(item));
    
    variables.put("receiver", Variable.getString("user@gmail.com"));
    variables.put("message", Variable.getString("Hello User!!Thank you for buying a " + item));
    return variables;
  }
}
