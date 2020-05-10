package org.github.theawesomenayak.camunda.registry;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.genesys.simpleclients.camunda.worker.ExternalWorker;
import org.github.theawesomenayak.camunda.worker.ChargeCardWorker;
import org.github.theawesomenayak.camunda.worker.SendNotificationWorker;

@Getter
@AllArgsConstructor
public enum WorkerRegistry {

  CHARGE_CARD(ChargeCardWorker.class),

  SEND_NOTIFICATION(SendNotificationWorker.class);

  private final Class<? extends ExternalWorker> workerClass;
}
