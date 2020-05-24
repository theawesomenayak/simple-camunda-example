package org.github.theawesomenayak.service;

import org.github.theawesomenayak.model.PaymentInstrument;

public interface PaymentService {

  long checkBalance(String customerId, PaymentInstrument paymentInstrument);

  void charge(PaymentInstrument paymentInstrument, long amount);

  void refund(PaymentInstrument paymentInstrument, long amount);
}
