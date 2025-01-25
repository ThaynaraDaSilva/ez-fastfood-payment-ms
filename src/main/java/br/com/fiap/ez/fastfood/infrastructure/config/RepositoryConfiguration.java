package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.adapters.out.repository.*;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RepositoryConfiguration {

	@Bean
	public ProductRepository productRepository(JpaProductRepository jpaProductRepository) {
		return new ProductRepositoryImpl(jpaProductRepository);
	}

	@Bean
	public CategoryRepository categoryRepository(JpaCategoryRepository jpaCategoryRepository) {
		return new CategoryRepositoryImpl(jpaCategoryRepository);
	}

	@Bean
	public PaymentRepository paymentRepository(JpaPaymentRepository paymentJpaRepository) {
		return new PaymentRepositoryImpl(paymentJpaRepository);
	}

}
