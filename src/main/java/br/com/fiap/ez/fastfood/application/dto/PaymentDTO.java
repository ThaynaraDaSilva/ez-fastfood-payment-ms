package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.Payment;

import java.time.ZonedDateTime;

public class PaymentDTO {

	private Long id;
	private Long orderId;
	private Long userId;
	private ZonedDateTime paymentDate;
	private Double paymentPrice;
	private String paymentStatus;

	public PaymentDTO(Payment payment) {
		this.id = payment.getId();
		this.orderId = payment.getOrderId();
		this.userId = payment.getUserId();
		this.paymentDate = payment.getPaymentDate();
		this.paymentPrice = payment.getPaymentPrice();
		this.paymentStatus = payment.getPaymentStatus().toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ZonedDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(ZonedDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(Double paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
