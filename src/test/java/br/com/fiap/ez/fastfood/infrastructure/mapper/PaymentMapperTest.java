package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class PaymentMapperTest {

    private final PaymentMapper paymentMapper = new PaymentMapper(); // Create an instance of PaymentMapper

    @Test
    public void testEntityToDomain() {
        PaymentEntity entity = new PaymentEntity();
        entity.setId(1L);
        entity.setOrderId(123L);
        entity.setUserId(456L);
        entity.setPaymentDate(ZonedDateTime.parse("2025-02-15T00:00:00Z"));  // Add time and timezone
        entity.setPaymentPrice(100.0);
        entity.setPaymentStatus(PaymentStatus.OK);

        Payment payment = PaymentMapper.entityToDomain(entity);

        assertNotNull(payment);
        assertEquals(1L, payment.getId());
        assertEquals(123L, payment.getOrderId());
        assertEquals(456L, payment.getUserId());
        assertEquals(100.0, payment.getPaymentPrice());
        assertEquals(PaymentStatus.OK, payment.getPaymentStatus());
    }

    @Test
    public void testEntityToDomainNull() {
        assertNull(PaymentMapper.entityToDomain((PaymentEntity) null));
    }

    @Test
    public void testDomainToEntity() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId(123L);
        payment.setUserId(456L);
        payment.setPaymentDate(ZonedDateTime.parse("2025-02-15T00:00:00Z"));
        payment.setPaymentPrice(100.0);
        payment.setPaymentStatus(PaymentStatus.OK);

        PaymentEntity entity = PaymentMapper.domainToEntity(payment);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(123L, entity.getOrderId());
        assertEquals(456L, entity.getUserId());
        assertEquals(100.0, entity.getPaymentPrice());
        assertEquals(PaymentStatus.OK, entity.getPaymentStatus());
    }

    @Test
    public void testDomainToEntityNull() {
        assertNull(PaymentMapper.domainToEntity((Payment) null));
    }

    @Test
    public void testEntityToDomainList() {
        PaymentEntity entity1 = new PaymentEntity();
        entity1.setId(1L);
        entity1.setOrderId(123L);
        entity1.setUserId(456L);
        entity1.setPaymentPrice(100.0);
        entity1.setPaymentStatus(PaymentStatus.OK);

        PaymentEntity entity2 = new PaymentEntity();
        entity2.setId(2L);
        entity2.setOrderId(124L);
        entity2.setUserId(457L);
        entity2.setPaymentPrice(150.0);
        entity2.setPaymentStatus(PaymentStatus.PENDING);

        List<PaymentEntity> entities = Arrays.asList(entity1, entity2);
        List<Payment> payments = PaymentMapper.entityToDomain(entities);

        assertEquals(2, payments.size());
        assertEquals(1L, payments.get(0).getId());
        assertEquals(2L, payments.get(1).getId());
    }

    @Test
    public void testDomainToEntityList() {
        Payment payment1 = new Payment();
        payment1.setId(1L);
        payment1.setOrderId(123L);
        payment1.setUserId(456L);
        payment1.setPaymentPrice(100.0);
        payment1.setPaymentStatus(PaymentStatus.OK);

        Payment payment2 = new Payment();
        payment2.setId(2L);
        payment2.setOrderId(124L);
        payment2.setUserId(457L);
        payment2.setPaymentPrice(150.0);
        payment2.setPaymentStatus(PaymentStatus.PENDING);

        List<Payment> payments = Arrays.asList(payment1, payment2);
        List<PaymentEntity> entities = PaymentMapper.domainToEntity(payments);

        assertEquals(2, entities.size());
        assertEquals(1L, entities.get(0).getId());
        assertEquals(2L, entities.get(1).getId());
    }

    @Test
    public void testDomainToResponseDto() {
        Payment payment = new Payment();
        payment.setOrderId(123L);
        payment.setUserId(456L);
        payment.setPaymentPrice(100.0);

        PaymentDTO dto = PaymentMapper.domainToResponseDto(payment);

        assertNotNull(dto);
        assertEquals(123L, dto.getOrderId());
        assertEquals(456L, dto.getUserId());
        assertEquals(100.0, dto.getPaymentPrice());
    }

    @Test
    public void testDTOtoDomain() {
        PaymentDTO dto = new PaymentDTO();
        dto.setOrderId(123L);
        dto.setUserId(456L);
        dto.setPaymentPrice(100.0);

        Payment payment = paymentMapper.DTOtoDomain(dto); // Use the instance of PaymentMapper

        assertNotNull(payment);
        assertEquals(123L, payment.getOrderId());
        assertEquals(456L, payment.getUserId());
        assertEquals(100.0, payment.getPaymentPrice());
        assertEquals(PaymentStatus.PENDING, payment.getPaymentStatus()); // Default value
    }

    @Test
    public void testDTOtoDomainNull() {
        assertNull(paymentMapper.DTOtoDomain(null)); // Check if null returns null without throwing an exception
    }
}
