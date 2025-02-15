package br.com.fiap.ez.fastfood.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    private Payment payment;
    private ZonedDateTime paymentDate;

    @BeforeEach
    public void setUp() {
        paymentDate = ZonedDateTime.now();
        payment = new Payment(1L, 2L, 3L, paymentDate, 100.0, PaymentStatus.OK);
    }

    @Test
    public void testDefaultConstructor() {
        Payment defaultPayment = new Payment();
        assertNotNull(defaultPayment);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(1L, payment.getId());
        assertEquals(3L, payment.getUserId());
        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(100.0, payment.getPaymentPrice());
        assertEquals(PaymentStatus.OK, payment.getPaymentStatus());
    }

    @Test
    public void testSetId() {
        payment.setId(4L);
        assertEquals(4L, payment.getId());
    }

    @Test
    public void testSetOrderId() {
        payment.setOrderId(5L);
        assertEquals(5L, payment.getOrderId());
    }

    @Test
    public void testSetUserId() {
        payment.setUserId(6L);
        assertEquals(6L, payment.getUserId());
    }

    @Test
    public void testSetPaymentDate() {
        ZonedDateTime newPaymentDate = ZonedDateTime.now().plusDays(1);
        payment.setPaymentDate(newPaymentDate);
        assertEquals(newPaymentDate, payment.getPaymentDate());
    }

    @Test
    public void testSetPaymentPrice() {
        payment.setPaymentPrice(200.0);
        assertEquals(200.0, payment.getPaymentPrice());
    }

    @Test
    public void testSetPaymentPriceInvalid() {
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentPrice(0.0));
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentPrice(-1.0));
    }

    @Test
    public void testSetPaymentStatus() {
        payment.setPaymentStatus(PaymentStatus.PENDING);
        assertEquals(PaymentStatus.PENDING, payment.getPaymentStatus());
    }
}