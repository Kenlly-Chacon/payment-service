package com.hiberus.paymentservice.domain.port.in;

// CORREGIDO: payment -> paymentservice
import com.hiberus.paymentservice.domain.model.PaymentOrder;

public interface PaymentUseCase {
    PaymentOrder initiatePayment(PaymentOrder request);
    PaymentOrder getPaymentDetails(String id);
    PaymentOrder getPaymentStatus(String id);
}