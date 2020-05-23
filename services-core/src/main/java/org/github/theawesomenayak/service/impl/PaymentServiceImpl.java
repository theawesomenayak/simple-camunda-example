package org.github.theawesomenayak.service.impl;

import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.ServiceUtils;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.service.PaymentService;

@Slf4j
@Named
public final class PaymentServiceImpl implements PaymentService {

  @Override
  public double checkBalance(final String customerId, final PaymentInstrument paymentInstrument) {

    log.info("Checking {} balance for customer {}...", paymentInstrument.name(), customerId);
    ServiceUtils.delay();
    return ThreadLocalRandom.current().nextDouble();
  }

  @Override
  public void charge(final PaymentInstrument paymentInstrument, final Long amount) {

    log.info("Charging {} with an amount of €{}...", paymentInstrument.name(), amount);
    ServiceUtils.delay();
  }

  @Override
  public void refund(final PaymentInstrument paymentInstrument, final Long amount) {

    log.info("Refunding an amount of €{} to {}...", amount, paymentInstrument.name());
    ServiceUtils.delay();
  }
}
