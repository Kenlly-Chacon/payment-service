package com.hiberus.paymentservice.domain.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad agnóstica de infraestructura.
 * Representa la regla de negocio pura.
 */
@Data
@Builder
public class PaymentOrder {
    private String paymentOrderId;
    private String externalReference;
    private String debtorIban;
    private String creditorIban;
    private BigDecimal amount;
    private String currency;
    private String remittanceInformation;
    private LocalDate requestedExecutionDate;

    // Estado y auditoría
    private String status;
    private LocalDateTime lastUpdate;
}
