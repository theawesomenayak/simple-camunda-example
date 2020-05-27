package org.github.theawesomenayak.camunda.config;

import static org.github.theawesomenayak.camunda.config.Topics.CHARGE_CARD;
import static org.github.theawesomenayak.camunda.config.Topics.CHECK_WALLET;
import static org.github.theawesomenayak.camunda.config.Topics.DEDUCT_WALLET;
import static org.github.theawesomenayak.camunda.config.Topics.REFUND_WALLET;
import static org.github.theawesomenayak.camunda.config.Topics.SEND_NOTIFICATION;

import lombok.AllArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.github.theawesomenayak.camunda.ExternalWorker;
import org.github.theawesomenayak.camunda.handler.ChargeCardHandler;
import org.github.theawesomenayak.camunda.handler.CheckWalletHandler;
import org.github.theawesomenayak.camunda.handler.DeductWalletHandler;
import org.github.theawesomenayak.camunda.handler.RefundWalletHandler;
import org.github.theawesomenayak.camunda.handler.SendNotificationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WorkerConfiguration {

  private static final int NUMBER_OF_THREADS = 1;

  private final ExternalTaskClient taskClient;

  @Bean
  ExternalWorker chargeCardWorker(final ChargeCardHandler taskHandler) {

    return new ExternalWorker(CHARGE_CARD, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker checkWalletWorker(final CheckWalletHandler taskHandler) {

    return new ExternalWorker(CHECK_WALLET, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker deductWalletWorker(final DeductWalletHandler taskHandler) {

    return new ExternalWorker(DEDUCT_WALLET, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker refundWalletWorker(final RefundWalletHandler taskHandler) {

    return new ExternalWorker(REFUND_WALLET, taskClient, taskHandler, NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker sendNotificationWorker(final SendNotificationHandler taskHandler) {

    return new ExternalWorker(SEND_NOTIFICATION, taskClient, taskHandler, NUMBER_OF_THREADS);
  }
}
