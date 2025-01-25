package br.com.fiap.ez.fastfood.infrastructure.config;

import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public ProductUseCase productUseCase(ProductRepository productRepository, CategoryRepository categoryRepository) {
		return new ProductUseCase(productRepository, categoryRepository);
	}

	@Bean
	public PaymentUseCase paymentUseCase(PaymentRepository paymentRepository) {
		return new PaymentUseCase(paymentRepository);
	}
}