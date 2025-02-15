package br.com.fiap.ez.fastfood.application.usecases;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentUseCase paymentUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterPayment() {
        Long orderId = 1L;
        Long userId = 2L;
        Double totalPrice = 100.0;

        paymentUseCase.registerPayment(orderId, userId, totalPrice);

        verify(paymentRepository, times(1)).registerPayment(any(Payment.class));
    }

    @Test
    public void testRegisterPaymentInvalidData() {
        assertThrows(BusinessException.class, () -> paymentUseCase.registerPayment(null, 2L, 100.0));
        assertThrows(BusinessException.class, () -> paymentUseCase.registerPayment(1L, 2L, 0.0));
        assertThrows(BusinessException.class, () -> paymentUseCase.registerPayment(1L, 2L, -1.0));
    }

    @Test
    public void testCheckPaymentStatus() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setOrderId(1L);
        payment.setUserId(2L);
        payment.setPaymentPrice(100.0);
        payment.setPaymentStatus(PaymentStatus.OK);
        payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        when(paymentRepository.findPaymentById(paymentId)).thenReturn(payment);

        PaymentDTO paymentDTO = paymentUseCase.checkPaymentStatus(paymentId);

        assertNotNull(paymentDTO);
        assertEquals(paymentId, paymentDTO.getOrderId());
    }

    @Test
    public void testCheckPaymentStatusNotFound() {
        Long paymentId = 1L;
        when(paymentRepository.findPaymentById(paymentId)).thenReturn(null);

        assertThrows(BusinessException.class, () -> paymentUseCase.checkPaymentStatus(paymentId));
    }

    @Test
    public void testSendPaymentToBank() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setOrderId(1L);
        payment.setUserId(2L);
        payment.setPaymentPrice(100.0);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        when(paymentRepository.findPaymentById(paymentId)).thenReturn(payment);

        paymentUseCase.sendPaymentToBank(paymentId);

        verify(paymentRepository, times(1)).updatePaymentStatus(any(Payment.class));
    }

    @Test
    public void testSendPaymentToBankNotFound() {
        Long paymentId = 1L;
        when(paymentRepository.findPaymentById(paymentId)).thenReturn(null);

        assertThrows(BusinessException.class, () -> paymentUseCase.sendPaymentToBank(paymentId));
    }
}