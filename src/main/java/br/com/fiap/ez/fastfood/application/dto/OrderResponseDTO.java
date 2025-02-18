package br.com.fiap.ez.fastfood.application.dto;

public class OrderResponseDTO {
	private String orderResponseStatus;

	public OrderResponseDTO(String orderResponseStatus) {
		this.orderResponseStatus = orderResponseStatus;
	}

	public String getOrderResponseStatus() {
		return orderResponseStatus;
	}

	public void setOrderResponseStatus(String orderResponseStatus) {
		this.orderResponseStatus = orderResponseStatus;
	}
}
