package org.github.theawesomenayak.camunda.demo;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.AMOUNT;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.APPROVED_MESSAGE;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.CUSTOMER_ID;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.ITEM;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.RECEIVER;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.REJECTED_MESSAGE;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.camunda.client.CamundaClient;
import org.github.theawesomenayak.camunda.client.variable.Variable;
import org.github.theawesomenayak.camunda.client.variable.VariableMap;
import org.github.theawesomenayak.camunda.workflow.Workflow;

@Slf4j
@Named
@AllArgsConstructor
public final class PaymentRetrieval implements Workflow {

  private static final String MESSAGE_TEMPLATE = "Hello User!! Your order of €%d for %s was %s";

  private final CamundaClient camundaClient;

  @Override
  public void deploy() {

    camundaClient.createDeployment("Payment Retrieval",
        "templates/handle-payment.bpmn",
        "templates/make-payment.bpmn",
        "templates/approve-payment.dmn");
  }

  @Override
  public void startProcess(final int numberOfProcesses) {

    final ExecutorService executorService = Executors.newSingleThreadExecutor();

    for (int i = 0; i < numberOfProcesses; i++) {
      executorService.execute(() -> {
        final Map<String, Variable> variables = getProcessVariables();
        log.info("Starting process with {}", variables);
        camundaClient.startProcess("handle-payment", variables);
      });
    }
  }

  private Map<String, Variable> getProcessVariables() {

    final long amount = ThreadLocalRandom.current().nextLong(100, 1500);
    final String[] items = {"Phone", "Laptop", "Charger"};
    final String item = items[ThreadLocalRandom.current().nextInt(0, items.length)];
    return VariableMap.newInstance()
        .putValue(CUSTOMER_ID, UUID.randomUUID().toString())
        .putValue(ITEM, item)
        .putValue(AMOUNT, amount)
        .putValue(RECEIVER, "user@gmail.com")
        .putValue(APPROVED_MESSAGE, String.format(MESSAGE_TEMPLATE, amount, item, "approved"))
        .putValue(REJECTED_MESSAGE, String.format(MESSAGE_TEMPLATE, amount, item, "rejected"))
        .getVariables();
  }
}
