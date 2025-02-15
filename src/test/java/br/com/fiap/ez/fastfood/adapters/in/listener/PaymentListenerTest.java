package br.com.fiap.ez.fastfood.adapters.in.listener;

import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.infrastructure.config.AmazonSQSProperties;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class PaymentListenerTest {

    @Mock
    private PaymentUseCase paymentUseCase;

    @Mock
    private SqsAsyncClient sqsAsyncClient;

    @Mock
    private AmazonSQSProperties amazonSQSProperties;

    @InjectMocks
    private PaymentListener paymentListener;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String queueUrl = "https://sqs.us-east-1.amazonaws.com/123456789012/payment-queue";

    @Test
    void testInitMethod() {
        assertDoesNotThrow(() -> paymentListener.init());
    }

    @Test
    void testPollMessagesFromQueue_WhenMessageIsReceived_ShouldProcessPayment() throws Exception {
        PaymentDTO paymentDTO = new PaymentDTO(1L, 2L, 100.0);
        String messageBody = objectMapper.writeValueAsString(paymentDTO);

        Message message = Message.builder()
                .body(messageBody)
                .receiptHandle("receipt-handle-123")
                .build();

        ReceiveMessageResponse receiveMessageResponse = ReceiveMessageResponse.builder()
                .messages(List.of(message))
                .build();

        when(sqsAsyncClient.receiveMessage(any(ReceiveMessageRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(receiveMessageResponse));

        paymentListener.pollMessagesFromQueue();

        verify(paymentUseCase, times(1)).registerPayment(1L, 2L, 100.0);

        verify(sqsAsyncClient, times(1)).deleteMessage(any(DeleteMessageRequest.class));
    }

    @Test
    void testPollMessagesFromQueue_WhenNoMessages_ShouldNotProcessPayment() {
        ReceiveMessageResponse receiveMessageResponse = ReceiveMessageResponse.builder().messages(List.of()).build();

        when(sqsAsyncClient.receiveMessage(any(ReceiveMessageRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(receiveMessageResponse));

        paymentListener.pollMessagesFromQueue();

        verify(paymentUseCase, never()).registerPayment(anyLong(), anyLong(), anyDouble());
        verify(sqsAsyncClient, never()).deleteMessage(any(DeleteMessageRequest.class));
    }
}
