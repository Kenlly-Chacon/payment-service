package com.hiberus.paymentservice.infrastructure.outbound.persistence.mapper;

// CORREGIDO: payment -> paymentservice
import com.hiberus.paymentservice.domain.model.PaymentOrder;
import com.hiberus.paymentservice.infrastructure.outbound.persistence.entity.PaymentOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {

    @Mapping(source = "paymentOrderId", target = "id")
    PaymentOrderEntity toEntity(PaymentOrder domain);

    @Mapping(source = "id", target = "paymentOrderId")
    PaymentOrder toDomain(PaymentOrderEntity entity);
}