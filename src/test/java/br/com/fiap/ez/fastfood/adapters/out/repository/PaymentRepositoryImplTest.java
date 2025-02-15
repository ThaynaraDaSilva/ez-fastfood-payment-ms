package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PaymentRepositoryImplTest {

    @Mock
    private JpaPaymentRepository jpaPaymentRepository;

    @InjectMocks
    private PaymentRepositoryImpl paymentRepositoryImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterPayment() {
        Payment payment = new Payment();
        PaymentEntity entity = PaymentMapper.domainToEntity(payment);
        when(jpaPaymentRepository.save(any(PaymentEntity.class))).thenReturn(entity);

        Payment result = paymentRepositoryImpl.registerPayment(payment);

        assertEquals(payment, result);
        verify(jpaPaymentRepository, times(1)).save(any(PaymentEntity.class));
    }

    @Test
    public void testFindPaymentById() {
        Long paymentId = 1L;
        PaymentEntity entity = new PaymentEntity();
        entity.setId(paymentId);
        entity.setOrderId(1L);
        entity.setUserId(2L);
        entity.setPaymentPrice(100.0);
        entity.setPaymentStatus(PaymentStatus.OK);
        entity.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        when(jpaPaymentRepository.findPaymentById(paymentId)).thenReturn(entity);

        Payment result = paymentRepositoryImpl.findPaymentById(paymentId);

        assertNotNull(result);
        verify(jpaPaymentRepository, times(1)).findPaymentById(paymentId);
    }

    @Test
    public void testUpdatePaymentStatus() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);
        payment.setPaymentStatus(PaymentStatus.OK);
        payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

        PaymentEntity entity = new PaymentEntity();
        when(jpaPaymentRepository.findPaymentById(paymentId)).thenReturn(entity);

        paymentRepositoryImpl.updatePaymentStatus(payment);

        verify(jpaPaymentRepository, times(1)).save(entity);
    }

    @Test
    public void testUpdatePaymentStatusNotFound() {
        Long paymentId = 1L;
        Payment payment = new Payment();
        payment.setId(paymentId);

        when(jpaPaymentRepository.findPaymentById(paymentId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> paymentRepositoryImpl.updatePaymentStatus(payment));
    }
}