package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment API", description = "Webhook to confirm or deny a order's payment")
public class PaymentController {

	private final PaymentUseCase paymentUseCase;

	public PaymentController(PaymentUseCase paymentUseCase) {
		this.paymentUseCase = paymentUseCase;
	}

	@PostMapping("/webhook/status")
	public ResponseEntity<?> registerPaymentStatus(@RequestBody PaymentDTO paymentDTO) {

		return new ResponseEntity<>(paymentUseCase.registerPaymentStatus(paymentDTO), HttpStatus.OK);

	}

	@GetMapping(path = "/check-status")
	public ResponseEntity<?> checkPaymentStatus(@Parameter Long paymentId) {

		return new ResponseEntity<>(paymentUseCase.checkPaymentStatus(paymentId), HttpStatus.OK);
	}

}
