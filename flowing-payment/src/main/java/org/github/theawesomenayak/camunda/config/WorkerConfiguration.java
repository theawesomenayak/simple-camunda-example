package org.github.theawesomenayak.camunda.config;

import lombok.AllArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.github.theawesomenayak.camunda.ExternalWorker;
import org.github.theawesomenayak.camunda.handler.ApprovePaymentHandler;
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

  private final ExternalTaskClient externalTaskClient;

  @Bean
  ExternalWorker approvePaymentWorker(final ApprovePaymentHandler externalTaskHandler) {

    return new ExternalWorker("approve-payment", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker chargeCardWorker(final ChargeCardHandler externalTaskHandler) {

    return new ExternalWorker("charge-card", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker checkWalletWorker(final CheckWalletHandler externalTaskHandler) {

    return new ExternalWorker("check-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker deductWalletWorker(final DeductWalletHandler externalTaskHandler) {

    return new ExternalWorker("deduct-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker refundWalletWorker(final RefundWalletHandler externalTaskHandler) {

    return new ExternalWorker("refund-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker sendNotificationWorker(final SendNotificationHandler externalTaskHandler) {

    return new ExternalWorker("send-notification", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }
}
