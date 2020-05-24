package org.github.theawesomenayak.camunda.config;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.github.theawesomenayak.camunda.demo.PaymentDemo;
import org.springframework.boot.SpringBootConfiguration;

@AllArgsConstructor
@SpringBootConfiguration
public class PaymentConfig {

  private final PaymentDemo paymentDemo;

  @PostConstruct
  void start() {

    paymentDemo.run();
  }
}
