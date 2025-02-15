package br.com.fiap.ez.fastfood.infrastructure.persistence;

import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentEntityTest {

    @Test
    void testPaymentEntityConstructorAndGetters() {
        ZonedDateTime paymentDate = ZonedDateTime.now();
        PaymentEntity paymentEntity = new PaymentEntity(1L, 123L, 456L, paymentDate, 100.0, PaymentStatus.OK);

        // Verify constructor and getters
        assertEquals(1L, paymentEntity.getId());
        assertEquals(123L, paymentEntity.getOrderId());
        assertEquals(456L, paymentEntity.getUserId());
        assertEquals(paymentDate, paymentEntity.getPaymentDate());
        assertEquals(100.0, paymentEntity.getPaymentPrice());
        assertEquals(PaymentStatus.OK, paymentEntity.getPaymentStatus());
    }

    @Test
    void testSetters() {
        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setId(1L);
        paymentEntity.setOrderId(123L);
        paymentEntity.setUserId(456L);
        paymentEntity.setPaymentDate(ZonedDateTime.now());
        paymentEntity.setPaymentPrice(100.0);
        paymentEntity.setPaymentStatus(PaymentStatus.PENDING);

        // Verify setters
        assertEquals(1L, paymentEntity.getId());
        assertEquals(123L, paymentEntity.getOrderId());
        assertEquals(456L, paymentEntity.getUserId());
        assertNotNull(paymentEntity.getPaymentDate());
        assertEquals(100.0, paymentEntity.getPaymentPrice());
        assertEquals(PaymentStatus.PENDING, paymentEntity.getPaymentStatus());
    }

    @Test
    void testDefaultConstructor() {
        PaymentEntity paymentEntity = new PaymentEntity();

        // Verify default constructor initializes fields to null or default values
        assertNull(paymentEntity.getId());
        assertNull(paymentEntity.getOrderId());
        assertNull(paymentEntity.getUserId());
        assertNull(paymentEntity.getPaymentDate());
        assertNull(paymentEntity.getPaymentPrice());
        assertNull(paymentEntity.getPaymentStatus());
    }
}
