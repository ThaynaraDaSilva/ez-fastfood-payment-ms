package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentDTOTest {

    private PaymentDTO paymentDTO;

    @BeforeEach
    public void setUp() {
        paymentDTO = new PaymentDTO(1L, 2L, 100.0);
    }

    @Test
    public void testDefaultConstructor() {
        PaymentDTO defaultPaymentDTO = new PaymentDTO();
        assertNotNull(defaultPaymentDTO);
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(1L, paymentDTO.getOrderId());
        assertEquals(2L, paymentDTO.getUserId());
        assertEquals(100.0, paymentDTO.getPaymentPrice());
    }

    @Test
    public void testSetOrderId() {
        paymentDTO.setOrderId(3L);
        assertEquals(3L, paymentDTO.getOrderId());
    }

    @Test
    public void testSetUserId() {
        paymentDTO.setUserId(4L);
        assertEquals(4L, paymentDTO.getUserId());
    }

    @Test
    public void testSetPaymentPrice() {
        paymentDTO.setPaymentPrice(200.0);
        assertEquals(200.0, paymentDTO.getPaymentPrice());
    }
}