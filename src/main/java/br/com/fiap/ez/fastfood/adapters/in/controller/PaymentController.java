package br.com.fiap.ez.fastfood.adapters.in.controller;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
//import br.com.fiap.ez.fastfood.application.dto.PaymentResponseDTO;
import br.com.fiap.ez.fastfood.application.usecases.PaymentUseCase;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import io.swagger.v3.oas.annotations.Hidden;
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

	@PostMapping("/register-payment-record")
	public ResponseEntity<PaymentDTO> registerNewPayment(
			@RequestParam Long orderId,
			@RequestParam(required = false) Long userId,
			@RequestParam Double totalPrice
	) {
		paymentUseCase.registerPayment(orderId, userId, totalPrice);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/check-status/{paymentId}")
	public ResponseEntity<PaymentDTO> checkPaymentStatus(@PathVariable Long paymentId) {
		PaymentDTO paymentDTO = paymentUseCase.checkPaymentStatus(paymentId);
		return ResponseEntity.ok(paymentDTO);
	}
	
	@PutMapping("/send-to-bank/{paymentId}")
	public ResponseEntity<String> sendPaymentToBank(@PathVariable Long paymentId) {
		paymentUseCase.sendPaymentToBank(paymentId);
		return ResponseEntity.ok("Pagamento enviado para a instituição financeira e status atualizado para OK");
	}

}
