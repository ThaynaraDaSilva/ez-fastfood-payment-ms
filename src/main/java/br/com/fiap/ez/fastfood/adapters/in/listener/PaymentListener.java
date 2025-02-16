package br.com.fiap.ez.fastfood.adapters.in.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.infrastructure.config.AmazonSQSProperties;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import jakarta.annotation.PostConstruct;

@Component
public class PaymentListener {

	 private final PaymentUseCase paymentUseCase;
	    private final ObjectMapper objectMapper;
	    private final SqsAsyncClient sqsAsyncClient;
	    private final AmazonSQSProperties amazonSQSProperties;
	    
	    @PostConstruct
	    public void init() {
	        System.out.println("PaymentListener foi registrado e está pronto para consumir mensagens.");
	    }

	    @Autowired
	    public PaymentListener(PaymentUseCase paymentUseCase, SqsAsyncClient sqsAsyncClient, AmazonSQSProperties amazonSQSProperties) {
	        this.paymentUseCase = paymentUseCase;
	        this.sqsAsyncClient = sqsAsyncClient;
	        this.amazonSQSProperties = amazonSQSProperties;
	        this.objectMapper = new ObjectMapper();
	    }

	    @Scheduled(fixedDelay = 120000)  // 5000 Runs every 5 seconds
	    public void pollMessagesFromQueue() {
	        //System.out.println("Checking for messages in SQS...");

	        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
	                .queueUrl(amazonSQSProperties.getPaymentQueueUrl())
	                .maxNumberOfMessages(5)  // Fetch up to 5 messages at a time
	                .build();

	        ReceiveMessageResponse response = sqsAsyncClient.receiveMessage(request).join();


	        if (response.messages().isEmpty()) {
	            System.out.println("No new messages found.");
	        } else {
	            for (Message message : response.messages()) {
	                processPayment(message.body(), message.receiptHandle());
	            }
	        }
	    }

	    private void processPayment(String message, String receiptHandle) {
	        try {
	           
	            PaymentDTO paymentDTO = objectMapper.readValue(message, PaymentDTO.class);

	            // Process payment logic
	            paymentUseCase.registerPayment(paymentDTO.getOrderId(), paymentDTO.getUserId(), paymentDTO.getPaymentPrice());
	            //System.out.println("Payment processed successfully: " + paymentDTO);

	            // Delete the message from the queue
	            deleteMessage(receiptHandle);
	            
	            notifyOrderPaymentStatus(paymentDTO);
	            
	        } catch (Exception e) {
	            System.err.println("Error processing payment: " + e.getMessage());
	        }
	    }

	    private void deleteMessage(String receiptHandle) {
	        DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
	                .queueUrl(amazonSQSProperties.getPaymentQueueUrl())
	                .receiptHandle(receiptHandle)
	                .build();

	        sqsAsyncClient.deleteMessage(deleteMessageRequest);
	        //System.out.println("Message deleted from queue.");
	    }
	    
	    private void notifyOrderPaymentStatus(PaymentDTO paymentDTO) {
	    	try {
	    		 paymentUseCase.notifyOrderPaymentStatus(paymentDTO);
	    	}catch(Exception e) {
	    		
	    	}
	    }

}
