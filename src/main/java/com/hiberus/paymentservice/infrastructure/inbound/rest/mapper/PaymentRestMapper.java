// file: src/main/java/com/hiberus/paymentservice/infrastructure/inbound/rest/mapper/PaymentRestMapper.java
package com.hiberus.paymentservice.infrastructure.inbound.rest.mapper;

import com.hiberus.paymentservice.domain.model.PaymentOrder;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentInitiationRequest;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentInitiationResponse;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentOrderDetails;
import com.hiberus.paymentservice.infrastructure.inbound.rest.model.PaymentStatusResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface PaymentRestMapper {

    // Request -> Domain (Desanidamiento)
    @Mapping(source = "debtorAccount.iban", target = "debtorIban")
    @Mapping(source = "creditorAccount.iban", target = "creditorIban")
    @Mapping(source = "instructedAmount.amount", target = "amount")
    @Mapping(source = "instructedAmount.currency", target = "currency")
    // Ignoramos campos que se generan en el servicio, no vienen del request
    @Mapping(target = "paymentOrderId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "lastUpdate", ignore = true)
    @Mapping(target = "remittanceInformation", source = "remittanceInformation")
    PaymentOrder toDomain(PaymentInitiationRequest request);

    // Domain -> Response (Initiation)
    PaymentInitiationResponse toInitiationResponse(PaymentOrder domain);

    // Domain -> Status Response
    @Mapping(source = "lastUpdate", target = "lastUpdate", qualifiedByName = "mapDateTime")
    PaymentStatusResponse toStatusResponse(PaymentOrder domain);

    // Domain -> Details Response (Re-anidamiento)
    @Mapping(source = "debtorIban", target = "debtorAccount.iban")
    @Mapping(source = "creditorIban", target = "creditorAccount.iban")
    @Mapping(source = "amount", target = "instructedAmount.amount")
    @Mapping(source = "currency", target = "instructedAmount.currency")
    @Mapping(source = "lastUpdate", target = "requestedExecutionDate", ignore = true) // Evitamos conflicto de fechas
    PaymentOrderDetails toDetailsResponse(PaymentOrder domain);

    // --- MÉTODOS DE AYUDA PARA FECHAS ---

    // Enseña a MapStruct a convertir LocalDateTime (BD) -> OffsetDateTime (API)
    @Named("mapDateTime")
    default OffsetDateTime mapDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        // Asumimos UTC para la API
        return localDateTime.atOffset(ZoneOffset.UTC);
    }
}