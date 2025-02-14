package br.com.fiap.ez.fastfood.application.usecases;

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

	public PaymentUseCase(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
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
		
		//System.out.println("#### PAYMENT USE CASE ###");

		paymentRepository.registerPayment(payment);
		//System.out.println("#### PAYMENT USE CASE REGISTER PAYMENT OK ###");
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
}
