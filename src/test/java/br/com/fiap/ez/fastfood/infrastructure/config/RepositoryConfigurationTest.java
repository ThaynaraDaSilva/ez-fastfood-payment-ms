package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.out.repository.JpaPaymentRepository;
import br.com.fiap.ez.fastfood.adapters.out.repository.PaymentRepositoryImpl;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RepositoryConfigurationTest {

    private RepositoryConfiguration repositoryConfiguration;

    @Mock
    private JpaPaymentRepository jpaPaymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        repositoryConfiguration = new RepositoryConfiguration();
    }

    @Test
    public void testPaymentRepository() {
        // Act
        PaymentRepository paymentRepository = repositoryConfiguration.paymentRepository(jpaPaymentRepository);

        // Assert
        assertNotNull(paymentRepository);
        assertTrue(paymentRepository instanceof PaymentRepositoryImpl);
    }
}