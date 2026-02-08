package com.hiberus.paymentservice.domain.port.out;

// CORREGIDO: payment -> paymentservice
import com.hiberus.paymentservice.domain.model.PaymentOrder;
import java.util.Optional;

public interface PaymentRepositoryPort {
    PaymentOrder save(PaymentOrder paymentOrder);
    Optional<PaymentOrder> findById(String id);
}