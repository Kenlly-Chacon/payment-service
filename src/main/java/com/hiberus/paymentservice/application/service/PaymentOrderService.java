package com.hiberus.paymentservice.application.service;

import com.hiberus.paymentservice.domain.model.PaymentOrder;
import com.hiberus.paymentservice.domain.port.in.PaymentUseCase;
import com.hiberus.paymentservice.domain.port.out.PaymentRepositoryPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentOrderService implements PaymentUseCase {

    private final PaymentRepositoryPort paymentRepository;

    @Override
    @Transactional
    public PaymentOrder initiatePayment(PaymentOrder request) {
        log.info("Iniciando orden de pago para referencia externa: {}", request.getExternalReference());

        // Lógica de Negocio: Generar ID (Formato PO-XXXX simulado o UUID)
        String newId = "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        request.setPaymentOrderId(newId);
        request.setStatus("ACCEPTED"); // Estado inicial BIAN
        request.setLastUpdate(LocalDateTime.now());

        return paymentRepository.save(request);
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentOrder getPaymentDetails(String id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment Order not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PaymentOrder getPaymentStatus(String id) {
        return getPaymentDetails(id); // Reutilizamos lógica de búsqueda
    }
}
