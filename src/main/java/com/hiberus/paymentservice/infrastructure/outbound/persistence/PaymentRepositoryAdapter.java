package com.hiberus.paymentservice.infrastructure.outbound.persistence;

import com.hiberus.paymentservice.domain.model.PaymentOrder;
import com.hiberus.paymentservice.domain.port.out.PaymentRepositoryPort;
import com.hiberus.paymentservice.infrastructure.outbound.persistence.entity.PaymentOrderEntity;
import com.hiberus.paymentservice.infrastructure.outbound.persistence.mapper.PaymentEntityMapper;
import com.hiberus.paymentservice.infrastructure.outbound.persistence.repository.SpringDataPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Adaptador de Salida.
 * Implementa el puerto del dominio usando tecnologías específicas (JPA).
 */
@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final SpringDataPaymentRepository jpaRepository;
    private final PaymentEntityMapper mapper;

    @Override
    public PaymentOrder save(PaymentOrder paymentOrder) {
        PaymentOrderEntity entity = mapper.toEntity(paymentOrder);
        PaymentOrderEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<PaymentOrder> findById(String id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}