package br.com.fiap.ez.fastfood.application.dto;

import java.math.BigDecimal;

public class PaymentRequestDTO {
	private String orderId;
	private String userId;
	private BigDecimal totalAmount;

	public String getOrderId() { return orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getUserId() { return userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public BigDecimal getTotalAmount() { return totalAmount; }
	public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
