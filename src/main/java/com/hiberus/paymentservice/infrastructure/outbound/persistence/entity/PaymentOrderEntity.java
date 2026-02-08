package com.hiberus.paymentservice.infrastructure.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderEntity {

    @Id
    @Column(name = "id")
    private String id; // PO-XXXX

    @Column(name = "external_ref", nullable = false)
    private String externalReference;

    @Column(name = "debtor_iban", nullable = false)
    private String debtorIban;

    @Column(name = "creditor_iban", nullable = false)
    private String creditorIban;

    @Column(name = "amount", precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "remittance_info")
    private String remittanceInformation;

    @Column(name = "req_exec_date")
    private LocalDate requestedExecutionDate;

    @Column(name = "status")
    private String status;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
