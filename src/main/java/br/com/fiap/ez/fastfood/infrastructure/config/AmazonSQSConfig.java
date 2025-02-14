package br.com.fiap.ez.fastfood.infrastructure.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.ez.fastfood.adapters.in.listener.PaymentListener;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AmazonSQSConfig {

	private final AmazonSQSProperties amazonSQSProperties;
    private final PaymentUseCase paymentUseCase;

    public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties, PaymentUseCase paymentUseCase) {
        this.amazonSQSProperties = amazonSQSProperties;
        this.paymentUseCase = paymentUseCase;
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder()
                .region(Region.of(amazonSQSProperties.getRegion()))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(
                                amazonSQSProperties.getAccessKey(),
                                amazonSQSProperties.getSecretKey()
                        )
                ))
                .endpointOverride(URI.create("http://localhost:4566")) // Correção aqui
                .build();
    }

    @Bean
    public PaymentListener paymentListener(SqsAsyncClient sqsAsyncClient) {
        return new PaymentListener(paymentUseCase, sqsAsyncClient, amazonSQSProperties);
    }
	 
}
