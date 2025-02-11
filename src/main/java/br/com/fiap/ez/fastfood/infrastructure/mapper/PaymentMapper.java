package br.com.fiap.ez.fastfood.infrastructure.mapper;

import br.com.fiap.ez.fastfood.application.dto.PaymentDTO;
import br.com.fiap.ez.fastfood.domain.model.Payment;
import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;
import br.com.fiap.ez.fastfood.infrastructure.persistence.PaymentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentMapper {

	public static Payment entityToDomain(PaymentEntity entity) {
		if (entity == null) {
			return null;
		}
		return new Payment(
				entity.getId(),
				entity.getOrderId(),
				entity.getUserId(),
				entity.getPaymentDate(),
				entity.getPaymentPrice(),
				entity.getPaymentStatus());
	}

	// Convert Payment (Domain) to PaymentEntity (Persistence)
	public static PaymentEntity domainToEntity(Payment payment) {
		if (payment == null) {
			return null;
		}

		PaymentEntity entity = new PaymentEntity();
		entity.setId(payment.getId());
		entity.setOrderId(payment.getOrderId());
		entity.setUserId(payment.getUserId());
		entity.setPaymentDate(payment.getPaymentDate());
		entity.setPaymentPrice(payment.getPaymentPrice());
		entity.setPaymentStatus(payment.getPaymentStatus());
		return entity;
	}

	// Convert a List of PaymentEntity (Persistence) to a List of Payment (Domain)
	public static List<Payment> entityToDomain(List<PaymentEntity> entities) {
		return entities.stream().map(PaymentMapper::entityToDomain).collect(Collectors.toList());
	}

	// Convert a List of Payment (Domain) to a List of PaymentEntity (Persistence)
	public static List<PaymentEntity> domainToEntity(List<Payment> payments) {
		return payments.stream().map(PaymentMapper::domainToEntity).collect(Collectors.toList());
	}

	public static PaymentDTO domainToResponseDto(Payment payment) {
		PaymentDTO dto = new PaymentDTO(payment);
		dto.setId(payment.getId());
		dto.setOrderId(payment.getOrderId());
		dto.setUserId(payment.getUserId());
		dto.setPaymentDate(payment.getPaymentDate());
		dto.setPaymentPrice(payment.getPaymentPrice());
		dto.setPaymentStatus(payment.getPaymentStatus().toString());
		return dto;
	}
	
   public Payment DTOtoDomain (PaymentDTO paymentDTO) {
	   Payment payment = new Payment();
	   
	   payment.setOrderId(paymentDTO.getOrderId());
	   
	   if(paymentDTO.getUserId()!=null){
		   payment.setUserId(paymentDTO.getUserId());
	   }
	
	   payment.setPaymentPrice(paymentDTO.getPaymentPrice());
	   payment.setPaymentStatus(PaymentStatus.PENDING);
       return payment;
   }

}
