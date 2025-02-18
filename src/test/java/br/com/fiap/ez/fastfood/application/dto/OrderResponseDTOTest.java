package br.com.fiap.ez.fastfood.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;

class OrderResponseDTOTest {

    @Test
    void testConstructorAndGetter() {
        // Arrange
        String expectedOrderResponseStatus = "SUCCESS";

        // Act
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO(expectedOrderResponseStatus);

        // Assert
        assertEquals(expectedOrderResponseStatus, orderResponseDTO.getOrderResponseStatus());
    }

    @Test
    void testSetter() {
        // Arrange
        OrderResponseDTO orderResponseDTO = new OrderResponseDTO("INITIAL_STATUS");
        String newOrderResponseStatus = "UPDATED_STATUS";

        // Act
        orderResponseDTO.setOrderResponseStatus(newOrderResponseStatus);

        // Assert
        assertEquals(newOrderResponseStatus, orderResponseDTO.getOrderResponseStatus());
    }
}