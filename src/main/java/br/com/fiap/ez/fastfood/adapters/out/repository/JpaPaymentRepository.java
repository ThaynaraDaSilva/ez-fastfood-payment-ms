package br.com.fiap.ez.fastfood.adapters.out.repository;

import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaPaymentRepository extends JpaRepository<PaymentEntity, Long>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM EZ_FASTFOOD_PAYMENT.PAYMENT WHERE id = :id")
	PaymentEntity findPaymentById(@Param("id") Long id);
	

}