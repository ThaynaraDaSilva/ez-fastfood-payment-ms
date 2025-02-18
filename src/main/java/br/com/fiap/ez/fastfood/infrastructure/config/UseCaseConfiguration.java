package br.com.fiap.ez.fastfood.infrastructure.config;


import br.com.fiap.ez.fastfood.adapters.out.http.OrderHttpClient;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.domain.repository.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

	@Bean
	public PaymentUseCase paymentUseCase(PaymentRepository paymentRepository, OrderHttpClient orderHttpClient) {
		return new PaymentUseCase(paymentRepository,orderHttpClient);
	}
}