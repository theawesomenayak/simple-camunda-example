package org.github.theawesomenayak.camunda.demo;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.client.CamundaClient;
import org.github.theawesomenayak.camunda.client.variable.Variable;
import org.github.theawesomenayak.camunda.client.variable.Variables;
import org.github.theawesomenayak.camunda.workflow.Workflow;

@Named
@AllArgsConstructor
public final class PaymentRetrieval implements Workflow {

  private static final String MESSAGE_TEMPLATE = "Hello User!! Your order of €%d for %s was %s";

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

    final long amount = ThreadLocalRandom.current().nextLong(100, 1500);
    final String[] items = {"Phone", "Laptop", "Charger"};
    final String item = items[ThreadLocalRandom.current().nextInt(0, items.length)];
    return Variables.createVariables()
        .putValue("userId", ThreadLocalRandom.current().nextLong(0, 1500))
        .putValue("amount", amount)
        .putValue("item", item)
        .putValue("receiver", "user@gmail.com")
        .putValue("approvedMessage", String.format(MESSAGE_TEMPLATE, amount, item, "approved"))
        .putValue("rejectedMessage", String.format(MESSAGE_TEMPLATE, amount, item, "rejected"))
        .getVariables();
  }
}
