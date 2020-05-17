package org.github.theawesomenayak.service;

import org.github.theawesomenayak.model.PaymentInstrument;

public interface PaymentService {

  double checkBalance(String customerId, PaymentInstrument paymentInstrument);

  void charge(PaymentInstrument paymentInstrument, Long amount);

  void refund(PaymentInstrument paymentInstrument, Long amount);
}
