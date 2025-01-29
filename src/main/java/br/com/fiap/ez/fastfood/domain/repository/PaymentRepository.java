package br.com.fiap.ez.fastfood.domain.repository;



import br.com.fiap.ez.fastfood.domain.model.Payment;

public interface PaymentRepository {
	
	Payment registerPayment(Payment payment);
	Payment findPaymentById(Long orderId);
	void updatePaymentStatus(Payment payment);

}
