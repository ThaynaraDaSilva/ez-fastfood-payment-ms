package br.com.fiap.ez.fastfood.adapters.out.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.fiap.ez.fastfood.application.dto.OrderRequestDTO;
import br.com.fiap.ez.fastfood.application.dto.OrderResponseDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

public interface OrderHttpClient {
	
	@FeignClient(name = "orderClient", url = "${microservices.order-url}")
	public interface PaymentHttpClient {
		
		 @PostMapping("/order")
		  OrderResponseDTO registerPayment(@RequestBody OrderRequestDTO OrderRequest);

	}


}
