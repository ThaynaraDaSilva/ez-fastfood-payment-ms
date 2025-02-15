package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentUseCase paymentUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterNewPayment() {
        Long orderId = 1L;
        Long userId = 2L;
        Double totalPrice = 100.0;

        ResponseEntity<PaymentDTO> response = paymentController.registerNewPayment(orderId, userId, totalPrice);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(paymentUseCase, times(1)).registerPayment(orderId, userId, totalPrice);
    }

    @Test
    public void testCheckPaymentStatus() {
        Long paymentId = 1L;
        PaymentDTO paymentDTO = new PaymentDTO();
        when(paymentUseCase.checkPaymentStatus(paymentId)).thenReturn(paymentDTO);

        ResponseEntity<PaymentDTO> response = paymentController.checkPaymentStatus(paymentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(paymentDTO, response.getBody());
        verify(paymentUseCase, times(1)).checkPaymentStatus(paymentId);
    }

    @Test
    public void testSendPaymentToBank() {
        Long paymentId = 1L;

        ResponseEntity<String> response = paymentController.sendPaymentToBank(paymentId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pagamento enviado para a instituição financeira e status atualizado para OK", response.getBody());
        verify(paymentUseCase, times(1)).sendPaymentToBank(paymentId);
    }
}