package com.hiberus.paymentservice.infrastructure.inbound.rest.controller;

import com.hiberus.paymentservice.application.service.PaymentOrderService;
import com.hiberus.paymentservice.domain.model.PaymentOrder;
// CORREGIDO: Importamos las interfaces generadas con el paquete correcto
import com.hiberus.paymentservice.infrastructure.inbound.rest.api.PaymentInitiationPaymentOrdersApi;
import com.hiberus.paymentservice.infrastructure.inbound.rest.mapper.PaymentRestMapper;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentInitiationRequest;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentInitiationResponse;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentOrderDetails;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentControllerAdapter implements PaymentInitiationPaymentOrdersApi {

    private final PaymentOrderService paymentUseCase;
    private final PaymentRestMapper mapper;

    @Override
    public ResponseEntity<PaymentInitiationResponse> initiatePayment(PaymentInitiationRequest request) {
        PaymentOrder domainRequest = mapper.toDomain(request);
        PaymentOrder createdOrder = paymentUseCase.initiatePayment(domainRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toInitiationResponse(createdOrder));
    }

    @Override
    public ResponseEntity<PaymentOrderDetails> getPaymentDetails(String paymentOrderId) {
        PaymentOrder order = paymentUseCase.getPaymentDetails(paymentOrderId);
        return ResponseEntity.ok(mapper.toDetailsResponse(order));
    }

    @Override
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(String paymentOrderId) {
        PaymentOrder order = paymentUseCase.getPaymentStatus(paymentOrderId);
        return ResponseEntity.ok(mapper.toStatusResponse(order));
    }
}