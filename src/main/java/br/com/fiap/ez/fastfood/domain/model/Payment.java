package br.com.fiap.ez.fastfood.domain.model;

import java.time.ZonedDateTime;

public class Payment {

    private Long id;
    private Long orderId;
    private Long userId;
    private ZonedDateTime paymentDate;
    private Double paymentPrice;
    private PaymentStatus paymentStatus;

    public Payment() {
    }

    public Payment(Long id, Long orderId, Long userId, ZonedDateTime paymentDate, Double paymentPrice,
                   PaymentStatus paymentStatus) {
        if (paymentPrice == null || paymentPrice <= 0) {
            throw new IllegalArgumentException("Pagamento deve ser maior que 0.");
        }
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.paymentDate = paymentDate;
        this.paymentPrice = paymentPrice;
        this.paymentStatus = paymentStatus;
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
        if (paymentPrice == null || paymentPrice <= 0) {
            throw new IllegalArgumentException("Pagamento deve ser maior que 0.");
        }
        this.paymentPrice = paymentPrice;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
