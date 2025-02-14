package br.com.fiap.ez.fastfood.application.dto;

import br.com.fiap.ez.fastfood.domain.model.Payment;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentDTO {

	//private Long id;
	private Long orderId;
	private Long userId;
	//private ZonedDateTime paymentDate;
	
	@JsonProperty("amount")
	private Double paymentPrice;
	//private String paymentStatus;

	 // Default constructor required by Jackson
    public PaymentDTO() {
    }
	
    
	
	public PaymentDTO(Long orderId, Long userId, Double paymentPrice) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.paymentPrice = paymentPrice;
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


	public Double getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(Double paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	

}
