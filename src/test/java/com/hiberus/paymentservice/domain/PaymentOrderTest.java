package com.hiberus.paymentservice.domain;

import com.hiberus.paymentservice.domain.model.PaymentOrder;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class PaymentOrderTest {

    @Test
    void shouldCreatePaymentOrderCorrectly() {
        // Given
        PaymentOrder order = PaymentOrder.builder()
                .externalReference("EXT-001")
                .amount(new BigDecimal("100.00"))
                .currency("EUR")
                .build();

        // Then
        assertNotNull(order);
        assertEquals("EXT-001", order.getExternalReference());
        assertEquals("EUR", order.getCurrency());
        assertNull(order.getPaymentOrderId()); // Aun no procesado
    }
}