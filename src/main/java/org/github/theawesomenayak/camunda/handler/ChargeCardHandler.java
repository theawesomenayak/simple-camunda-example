package org.github.theawesomenayak.camunda.handler;

import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.camunda.bpm.client.task.ExternalTaskService;

@Slf4j
@Named
public final class ChargeCardHandler implements ExternalTaskHandler {

  @SneakyThrows
  @Override
  public void execute(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final String item = externalTask.getVariable("item");
    final Long amount = externalTask.getVariable("amount");

    TimeUnit.SECONDS.sleep(10000);
    log.info("Charging credit card with an amount of €{} for the item {}...", amount, item);

    externalTaskService.complete(externalTask);
  }
}