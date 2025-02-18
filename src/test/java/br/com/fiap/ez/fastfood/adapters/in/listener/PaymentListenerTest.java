package br.com.fiap.ez.fastfood.adapters.in.listener;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
        when(amazonSQSProperties.getPaymentQueueUrl()).thenReturn("https://sqs.us-east-1.amazonaws.com/123456789012/payment-queue");

        paymentListener.pollMessagesFromQueue();

        // Captura o PaymentDTO passado para notifyOrderPaymentStatus
        ArgumentCaptor<PaymentDTO> paymentDTOCaptor = ArgumentCaptor.forClass(PaymentDTO.class);
        verify(paymentUseCase, times(1)).registerPayment(1L, 2L, 100.0);
        verify(paymentUseCase, times(1)).notifyOrderPaymentStatus(paymentDTOCaptor.capture());

        // Verifica se o PaymentDTO capturado é igual ao esperado
        PaymentDTO capturedPaymentDTO = paymentDTOCaptor.getValue();
        assertEquals(paymentDTO.getOrderId(), capturedPaymentDTO.getOrderId());
        assertEquals(paymentDTO.getUserId(), capturedPaymentDTO.getUserId());
        assertEquals(paymentDTO.getPaymentPrice(), capturedPaymentDTO.getPaymentPrice());
    }

    @Test
    void testPollMessagesFromQueue_WhenNoMessages_ShouldNotProcessPayment() {
        ReceiveMessageResponse receiveMessageResponse = ReceiveMessageResponse.builder().messages(List.of()).build();

        when(sqsAsyncClient.receiveMessage(any(ReceiveMessageRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(receiveMessageResponse));
        when(amazonSQSProperties.getPaymentQueueUrl()).thenReturn("https://sqs.us-east-1.amazonaws.com/123456789012/payment-queue");

        paymentListener.pollMessagesFromQueue();

        verify(paymentUseCase, never()).registerPayment(anyLong(), anyLong(), anyDouble());
        verify(sqsAsyncClient, never()).deleteMessage(any(DeleteMessageRequest.class));
    }
}