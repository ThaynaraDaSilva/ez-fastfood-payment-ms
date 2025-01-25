CREATE SCHEMA IF NOT EXISTS EZ_FASTFOOD_PAYMENT;

CREATE TABLE IF NOT EXISTS EZ_FASTFOOD_PAYMENT.PAYMENT (
    id BIGSERIAL PRIMARY KEY,
    order_id INT NOT NULL,
    user_id INT NULL,
    payment_date TIMESTAMP WITH TIME ZONE NULL,
    payment_price DECIMAL,
    payment_status VARCHAR(50)
);