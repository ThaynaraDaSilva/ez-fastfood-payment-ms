package br.com.fiap.ez.fastfood.adapters.in.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.infrastructure.config.AmazonSQSProperties;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import io.awspring.cloud.sqs.annotation.SqsListener;
import jakarta.annotation.PostConstruct;

@Component
public class PaymentListener {

	private final PaymentUseCase paymentUseCase;
	private final ObjectMapper objectMapper;
	private final SqsClient sqsClient;
	private final AmazonSQSProperties amazonSQSProperties;
	
	 @PostConstruct
	    public void init() {
	        System.out.println("PaymentListener foi registrado e está pronto para consumir mensagens.");
	    }

	@Autowired
	public PaymentListener(PaymentUseCase paymentUseCase, SqsClient sqsClient, AmazonSQSProperties amazonSQSProperties) {
		this.paymentUseCase = paymentUseCase;
		this.sqsClient = sqsClient;
		this.amazonSQSProperties = amazonSQSProperties;
		this.objectMapper = new ObjectMapper();
	}

	@SqsListener(value = "order-payment-queue")
	public void processPayment(String message, @Header("ReceiptHandle") String receiptHandle) {

		try {
			// Deserialize JSON into DTO
			PaymentDTO paymentDTO = objectMapper.readValue(message, PaymentDTO.class);

			// Call the existing use case method
			paymentUseCase.registerPayment(paymentDTO.getOrderId(), paymentDTO.getUserId(),
					paymentDTO.getPaymentPrice());
			System.err.println("########### Payment processed ###########");

			// Manually delete the message from the queue
			deleteMessage(receiptHandle);
		} catch (Exception e) {
			System.err.println("Error processing payment: " + e.getMessage());
		}
	}

	private void deleteMessage(String receiptHandle) {
		DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder().queueUrl(amazonSQSProperties.getPaymentQueueUrl())
				.receiptHandle(receiptHandle).build();

		sqsClient.deleteMessage(deleteMessageRequest);
		System.out.println("Message deleted from queue.");
	}

}
