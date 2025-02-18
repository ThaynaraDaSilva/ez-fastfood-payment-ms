package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.out.http.OrderHttpClient;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UseCaseConfigurationTest {

    @InjectMocks
    private UseCaseConfiguration useCaseConfiguration;

    @Mock
    private PaymentRepository paymentRepository;
    
    @Mock
    private OrderHttpClient orderHttpClient;
    

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPaymentUseCase() {
        // Act
        PaymentUseCase paymentUseCase = useCaseConfiguration.paymentUseCase(paymentRepository,orderHttpClient);

        // Assert
        assertNotNull(paymentUseCase);
        assertTrue(paymentUseCase instanceof PaymentUseCase);
    }
}