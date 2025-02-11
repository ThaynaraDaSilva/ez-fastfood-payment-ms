package br.com.fiap.ez.fastfood.adapters.in.listener;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;
//import io.awspring.cloud.messaging.listener.annotation.SqsListener;



@Component
public class PaymentListener {

	private final PaymentUseCase paymentUseCase;
	private final PaymentMapper paymentMapper;
	private final ObjectMapper objectMapper;

	public PaymentListener(PaymentUseCase paymentUseCase, PaymentMapper paymentMapper) {
		this.paymentUseCase = paymentUseCase;
		this.paymentMapper = paymentMapper;
		this.objectMapper = new ObjectMapper();

	}

	/*
	 * @SqsListener(value = "order-payment-queue", deletionPolicy =
	 * SqsMessageDeletionPolicy.ON_SUCCESS) public void processPayment(String
	 * message, @Header("MessageId") String messageId) { try { // Deserialize JSON
	 * into DTO PaymentDTO paymentDTO = objectMapper.readValue(message,
	 * PaymentDTO.class);
	 * 
	 * // Call the existing use case method
	 * paymentUseCase.registerPayment(paymentDTO.getOrderId(),
	 * paymentDTO.getUserId(), paymentDTO.getPaymentPrice());
	 * System.err.println("########### Payment processed ###########"); } catch
	 * (Exception e) { System.err.println("Error processing payment: " +
	 * e.getMessage()); } }
	 */

}
