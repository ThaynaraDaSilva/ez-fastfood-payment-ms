package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;

public class OrderRequestDTO {

	private Long orderId;
    private Long userId;
    private PaymentStatus paymentStatus;
	
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
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
    
    
    
}
