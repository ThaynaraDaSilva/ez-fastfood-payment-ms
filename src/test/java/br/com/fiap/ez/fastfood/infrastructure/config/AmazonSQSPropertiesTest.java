package br.com.fiap.ez.fastfood.infrastructure.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AmazonSQSPropertiesTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        AmazonSQSProperties properties = new AmazonSQSProperties();
        String paymentQueueUrl = "http://example.com/payment-queue";
        String paymentResultQueueUrl = "http://example.com/payment-result-queue";
        String region = "us-east-1";
        String accessKey = "dummyAccessKey";
        String secretKey = "dummySecretKey";

        // Act
        properties.setPaymentQueueUrl(paymentQueueUrl);
        properties.setPaymentResultQueueUrl(paymentResultQueueUrl);
        properties.setRegion(region);
        properties.setAccessKey(accessKey);
        properties.setSecretKey(secretKey);

        // Assert
        assertEquals(paymentQueueUrl, properties.getPaymentQueueUrl());
        assertEquals(paymentResultQueueUrl, properties.getPaymentResultQueueUrl());
        assertEquals(region, properties.getRegion());
        assertEquals(accessKey, properties.getAccessKey());
        assertEquals(secretKey, properties.getSecretKey());
    }
}