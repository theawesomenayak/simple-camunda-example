package org.github.theawesomenayak.camunda.handler;

import com.google.common.collect.ImmutableMap;
import javax.inject.Named;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.github.theawesomenayak.camunda.api.TaskHandler;

@Named("approve.payment.handler")
public final class ApprovePaymentHandler extends TaskHandler {

  @Override
  protected void handle(final ExternalTask externalTask,
      final ExternalTaskService externalTaskService) {

    final String item = externalTask.getVariable("item");
    final boolean approved = !"Phone".equals(item);
    externalTaskService.complete(externalTask, ImmutableMap.of("approved", approved));
  }
}