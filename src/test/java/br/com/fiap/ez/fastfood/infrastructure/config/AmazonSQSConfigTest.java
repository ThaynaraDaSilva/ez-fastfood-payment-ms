package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.in.listener.PaymentListener;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AmazonSQSConfigTest {

    @InjectMocks
    private AmazonSQSConfig amazonSQSConfig;

    @Mock
    private AmazonSQSProperties amazonSQSProperties;

    @Mock
    private PaymentUseCase paymentUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock the properties needed for SQS
        when(amazonSQSProperties.getRegion()).thenReturn("us-east-1");
        when(amazonSQSProperties.getAccessKey()).thenReturn("dummyAccessKey");
        when(amazonSQSProperties.getSecretKey()).thenReturn("dummySecretKey");
    }

    @Test
    public void testSqsAsyncClient() {
        // Act
        SqsAsyncClient sqsAsyncClient = amazonSQSConfig.sqsAsyncClient();

        // Assert
        assertNotNull(sqsAsyncClient);
    }

    @Test
    public void testPaymentListener() {
        // Act
        PaymentListener paymentListener = amazonSQSConfig.paymentListener(amazonSQSConfig.sqsAsyncClient());

        // Assert
        assertNotNull(paymentListener);
    }
}