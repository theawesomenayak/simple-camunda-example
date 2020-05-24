package org.github.theawesomenayak.camunda.config;

import javax.inject.Named;
import lombok.AllArgsConstructor;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.github.theawesomenayak.camunda.api.ExternalWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class WorkerConfiguration {

  private static final int NUMBER_OF_THREADS = 1;

  private final ExternalTaskClient externalTaskClient;

  @Bean
  ExternalWorker approvePaymentWorker(
      @Named("approve.payment.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("approve-payment", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker chargeCardWorker(
      @Named("charge.card.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("charge-card", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker checkWalletWorker(
      @Named("check.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("check-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker deductWalletWorker(
      @Named("deduct.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("deduct-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker refundWalletWorker(
      @Named("refund.wallet.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("refund-wallet", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }

  @Bean
  ExternalWorker sendNotificationWorker(
      @Named("send.notification.handler") final ExternalTaskHandler externalTaskHandler) {

    return new ExternalWorker("send-notification", externalTaskClient, externalTaskHandler,
        NUMBER_OF_THREADS);
  }
}
