package org.github.theawesomenayak.service.impl;

import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.github.theawesomenayak.ServiceUtils;
import org.github.theawesomenayak.model.PaymentInstrument;
import org.github.theawesomenayak.observability.Observe;
import org.github.theawesomenayak.service.PaymentService;

@Slf4j
@Named
public class PaymentServiceImpl implements PaymentService {

  @Observe
  @Override
  public long checkBalance(final String customerId, final PaymentInstrument paymentInstrument) {

    log.info("Checking {} balance for customer {}...", paymentInstrument.name(), customerId);
    ServiceUtils.delay();
    return ThreadLocalRandom.current().nextLong(800, 1500);
  }

  @Observe
  @Override
  public void charge(final PaymentInstrument paymentInstrument, final long amount) {

    log.info("Charging {} with an amount of €{}...", paymentInstrument.name(), amount);
    ServiceUtils.delay();
  }

  @Observe
  @Override
  public void refund(final PaymentInstrument paymentInstrument, final long amount) {

    log.info("Refunding an amount of €{} to {}...", amount, paymentInstrument.name());
    ServiceUtils.delay();
  }
}
