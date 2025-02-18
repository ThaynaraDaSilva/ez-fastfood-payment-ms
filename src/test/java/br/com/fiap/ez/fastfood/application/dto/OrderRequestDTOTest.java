package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.fiap.ez.fastfood.application.dto.OrderRequestDTO;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;

class OrderRequestDTOTest {

    @Test
    void testGettersAndSetters() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
        Long expectedOrderId = 1L;
        Long expectedUserId = 2L;
        PaymentStatus expectedPaymentStatus = PaymentStatus.OK;

        orderRequestDTO.setOrderId(expectedOrderId);
        orderRequestDTO.setUserId(expectedUserId);
        orderRequestDTO.setPaymentStatus(expectedPaymentStatus);

        assertEquals(expectedOrderId, orderRequestDTO.getOrderId());
        assertEquals(expectedUserId, orderRequestDTO.getUserId());
        assertEquals(expectedPaymentStatus, orderRequestDTO.getPaymentStatus());
    }
}