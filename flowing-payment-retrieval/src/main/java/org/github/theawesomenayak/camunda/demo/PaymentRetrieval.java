package org.github.theawesomenayak.camunda.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.client.CamundaClient;
import org.github.theawesomenayak.camunda.client.request.Variable;
import org.github.theawesomenayak.camunda.workflow.Workflow;

@Named
@AllArgsConstructor
public final class PaymentRetrieval implements Workflow {

  private final CamundaClient camundaClient;

  @Override
  public void deploy() {

    camundaClient.createDeployment("Payment Retrieval",
      "templates/payment.bpmn", "templates/approve-payment.dmn");
  }

  @Override
  public void startProcess(final int numberOfProcesses) {

    final ExecutorService executorService = Executors.newFixedThreadPool(4);

    for (int i = 0; i < numberOfProcesses; i++) {
      executorService.submit(() -> {
        final Map<String, Variable> variables = getProcessVariables();
        camundaClient.startProcess("payment-retrieval", variables);
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