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
		payment.setPaymentStatus(PaymentStatus.PENDING);
		payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));

		paymentRepository.registerPayment(payment);
	}

	public PaymentDTO registerPaymentStatus(PaymentDTO paymentDto) {
		Payment payment = paymentRepository.findPaymentById(paymentDto.getPaymentId());
		if (payment == null) {
			throw new BusinessException("Não existe pagamento com este id");
		}

		// payment
		if (payment.getPaymentStatus() == PaymentStatus.PENDING) {
			payment.setPaymentDate(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
			payment.setPaymentStatus(PaymentStatus.valueOf(paymentDto.getPaymentStatus().toUpperCase()));
			paymentRepository.registerPaymentStatus(payment);

			return PaymentMapper.domainToResponseDto(payment);
		} else {
			throw new BusinessException("Este pagamento já foi confirmado ou recusado.");
		}

	}

	public PaymentDTO checkPaymentStatus(Long paymentId) {
		Payment payment = paymentRepository.findPaymentById(paymentId);
		if (payment != null) {
			return PaymentMapper.domainToResponseDto(payment);
		} else {
			throw new BusinessException("Não existe pagamento com este id");
		}

	}
}
