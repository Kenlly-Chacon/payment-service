package com.hiberus.paymentservice.infrastructure.outbound.persistence.repository;


import com.hiberus.paymentservice.infrastructure.outbound.persistence.entity.PaymentOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentRepository extends JpaRepository<PaymentOrderEntity, String> {
}
