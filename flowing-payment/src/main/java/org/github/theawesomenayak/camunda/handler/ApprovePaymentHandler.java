package org.github.theawesomenayak.camunda.handler;

import static org.github.theawesomenayak.camunda.config.ProcessVariables.APPROVED;
import static org.github.theawesomenayak.camunda.config.ProcessVariables.ITEM;

import com.google.common.collect.ImmutableMap;
import javax.inject.Named;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.ExternalHandler;

@Named
public final class ApprovePaymentHandler extends ExternalHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final String item = externalTask.getVariable(ITEM.name());
    final boolean approved = !"Phone".equals(item);
    externalTaskService.complete(externalTask, ImmutableMap.of(APPROVED.name(), approved));
  }
}