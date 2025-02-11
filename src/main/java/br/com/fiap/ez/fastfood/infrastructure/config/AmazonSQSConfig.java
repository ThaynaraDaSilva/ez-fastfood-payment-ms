package br.com.fiap.ez.fastfood.infrastructure.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

@Configuration
public class AmazonSQSConfig {

	 private final AmazonSQSProperties amazonSQSProperties;

	    public AmazonSQSConfig(AmazonSQSProperties amazonSQSProperties) {
	        this.amazonSQSProperties = amazonSQSProperties;
	       
	    }

		/*
		 * @Bean public SqsClient sqsClient() { return SqsClient.builder()
		 * .region(Region.of(amazonSQSProperties.getRegion()))
		 * .credentialsProvider(StaticCredentialsProvider.create(
		 * AwsBasicCredentials.create( amazonSQSProperties.getAccessKey(),
		 * amazonSQSProperties.getSecretKey() ) ))
		 * .endpointOverride(URI.create(amazonSQSProperties.getPaymentQueueUrl())) //
		 * Use LocalStack or AWS URL .build(); }
		 */
	    
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
	                .endpointOverride(URI.create(amazonSQSProperties.getPaymentQueueUrl())) // Use LocalStack or AWS URL
	                .build();
	    }
	 
}
