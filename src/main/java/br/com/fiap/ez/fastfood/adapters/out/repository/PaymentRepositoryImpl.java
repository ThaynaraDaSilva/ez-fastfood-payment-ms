package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import br.com.fiap.ez.fastfood.infrastructure.mapper.PaymentMapper;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;

public class PaymentRepositoryImpl implements PaymentRepository {

	private final JpaPaymentRepository jpaPaymentRepository;

	public PaymentRepositoryImpl(JpaPaymentRepository jpaPaymentRepository) {

		this.jpaPaymentRepository = jpaPaymentRepository;
	}

	@Override
	public Payment registerPayment(Payment payment) {
		PaymentEntity entity = PaymentMapper.domainToEntity(payment);
		jpaPaymentRepository.save(entity);
		return payment;
	}

	@Override
	public Payment findPaymentById(Long orderId) {
		PaymentEntity entity =  jpaPaymentRepository.findPaymentById(orderId);
		return PaymentMapper.entityToDomain(entity);
	}

	@Override
	public void updatePaymentStatus(Payment payment) {
		PaymentEntity entity = jpaPaymentRepository.findPaymentById(payment.getId());
		if (entity != null) {
			entity.setPaymentStatus(payment.getPaymentStatus());
			entity.setPaymentDate(payment.getPaymentDate());
			jpaPaymentRepository.save(entity);
		} else {
			throw new IllegalArgumentException("Payment not found with ID: " + payment.getId());
		}
	}

}
