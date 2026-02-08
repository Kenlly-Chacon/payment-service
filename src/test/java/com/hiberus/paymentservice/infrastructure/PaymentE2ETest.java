package com.hiberus.paymentservice.infrastructure;

import com.hiberus.paymentservice.application.service.PaymentOrderService;
import com.hiberus.paymentservice.domain.model.PaymentOrder;
import com.hiberus.paymentservice.infrastructure.inbound.rest.controller.PaymentControllerAdapter;
import com.hiberus.paymentservice.infrastructure.inbound.rest.mapper.PaymentRestMapper;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.AccountReference;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.Amount;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentInitiationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentControllerAdapter.class)
class PaymentE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentOrderService paymentService;

    @MockBean // Importante mockear el mapper también o usar @Import
    private PaymentRestMapper mapper;

    @Test
    void shouldInitiatePaymentSuccessfully() throws Exception {
        // Configurar Mocks
        PaymentOrder mockedDomainOrder = PaymentOrder.builder()
                .paymentOrderId("PO-TEST-123")
                .status("ACCEPTED")
                .build();

        when(mapper.toDomain(any())).thenReturn(mockedDomainOrder);
        when(paymentService.initiatePayment(any())).thenReturn(mockedDomainOrder);

        // Simular respuesta del mapper de vuelta (simplificado)
        // Nota: En un entorno real se usaria @Import(PaymentRestMapperImpl.class) para usar el mapper real

        String jsonRequest = """
            {
              "externalReference": "EXT-1",
              "debtorAccount": { "iban": "ES123" },
              "creditorAccount": { "iban": "ES456" },
              "instructedAmount": { "amount": 100, "currency": "EUR" },
              "remittanceInformation": "Test",
              "requestedExecutionDate": "2025-10-30"
            }
            """;

        // Ejecutar y Verificar (Espera 201 Created)
        mockMvc.perform(post("/payment-initiation/payment-orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated());
    }
}