package org.github.theawesomenayak.camunda;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AllArgsConstructor
@SpringBootApplication(scanBasePackages = "org.github.theawesomenayak")
public class InvoiceApproval {

  public static void main(final String[] args) {

    SpringApplication.run(InvoiceApproval.class, args);
  }
}
