package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentRequestDTOTest {

    private PaymentRequestDTO paymentRequestDTO;

    @BeforeEach
    public void setUp() {
        paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setOrderId("123");
        paymentRequestDTO.setUserId("456");
        paymentRequestDTO.setTotalAmount(new BigDecimal("789.00"));
    }

    @Test
    public void testDefaultConstructor() {
        PaymentRequestDTO defaultPaymentRequestDTO = new PaymentRequestDTO();
        assertNotNull(defaultPaymentRequestDTO);
    }

    @Test
    public void testGetOrderId() {
        assertEquals("123", paymentRequestDTO.getOrderId());
    }

    @Test
    public void testSetOrderId() {
        paymentRequestDTO.setOrderId("789");
        assertEquals("789", paymentRequestDTO.getOrderId());
    }

    @Test
    public void testGetUserId() {
        assertEquals("456", paymentRequestDTO.getUserId());
    }

    @Test
    public void testSetUserId() {
        paymentRequestDTO.setUserId("789");
        assertEquals("789", paymentRequestDTO.getUserId());
    }

    @Test
    public void testGetTotalAmount() {
        assertEquals(new BigDecimal("789.00"), paymentRequestDTO.getTotalAmount());
    }

    @Test
    public void testSetTotalAmount() {
        paymentRequestDTO.setTotalAmount(new BigDecimal("123.45"));
        assertEquals(new BigDecimal("123.45"), paymentRequestDTO.getTotalAmount());
    }
}