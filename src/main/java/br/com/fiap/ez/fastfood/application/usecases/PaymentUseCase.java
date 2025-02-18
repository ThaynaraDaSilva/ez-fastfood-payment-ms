package br.com.fiap.ez.fastfood.application.usecases;


import br.com.fiap.ez.fastfood.adapters.out.http.OrderHttpClient;
import br.com.fiap.ez.fastfood.application.dto.OrderRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.frameworks.exception.BusinessException;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PaymentUseCase {

	private final PaymentRepository paymentRepository;
	private final OrderHttpClient orderHttpClient;

	public PaymentUseCase(PaymentRepository paymentRepository,OrderHttpClient orderHttpClient) {
		this.paymentRepository = paymentRepository;
		this.orderHttpClient = orderHttpClient;
	}

	public void registerPayment(Long orderId, Long userId, Double totalPrice) {
		if (orderId == null || totalPrice == null || totalPrice <= 0) {
			throw new BusinessException("Dados inválidos para registrar o pagamento.");
		}

		Payment payment = new Payment();

		payment.setOrderId(orderId);
		payment.setUserId(userId);
		payment.setPaymentPrice(totalPrice);
		payment.setPaymentStatus(PaymentStatus.OK);
		payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
		

		paymentRepository.registerPayment(payment);
		
		
		//PROCESSO A CORRIGIR APOS INTEGRACAO COM MERCADOPAGO
	}

	public PaymentDTO checkPaymentStatus(Long paymentId) {
		Payment payment = paymentRepository.findPaymentById(paymentId);
		if (payment != null) {
			return PaymentMapper.domainToResponseDto(payment);
		} else {
			throw new BusinessException("Não existe pagamento com este id");
		}
	}

	public void sendPaymentToBank(Long paymentId) {
		Payment payment = paymentRepository.findPaymentById(paymentId);
		if (payment == null) {
			throw new BusinessException("Não existe pagamento com este id");
		}

		System.out.println("Enviando pagamento para instituição financeira: " + paymentId);

		payment.setPaymentStatus(PaymentStatus.OK);
		payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

		paymentRepository.updatePaymentStatus(payment);
	}
	
	public void notifyOrderPaymentStatus (PaymentDTO paymentDTO) {
		try {

			OrderRequestDTO orderRequestDTO = new OrderRequestDTO();
			orderRequestDTO.setOrderId(paymentDTO.getOrderId());
			orderRequestDTO.setPaymentStatus(PaymentStatus.OK);
			if(paymentDTO.getUserId()!=null) {
				orderRequestDTO.setUserId(paymentDTO.getUserId());
			}
			System.out.println("Antes do método de notificação");
			orderHttpClient.notifyOrderPaymentStatus(orderRequestDTO);
			System.out.println("Passei pelo método de notificação");
			
			
		} catch (Exception e) {
		    throw new RuntimeException("Failed integration: " + e);
		}
	}
}
