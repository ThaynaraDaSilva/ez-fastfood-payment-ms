package br.com.fiap.ez.fastfood.infrastructure.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = "microservices.orderUrl=http://localhost:8080/orders")
class HttpClientPropertiesTest {

    private HttpClientProperties httpClientProperties;

    @BeforeEach
    void setUp() {
        httpClientProperties = new HttpClientProperties();
        httpClientProperties.setOrderUrl("http://localhost:8080/orders");
    }

    @Test
    void testGetOrderUrl() {
        assertEquals("http://localhost:8080/orders", httpClientProperties.getOrderUrl());
    }

    @Test
    void testSetOrderUrl() {
        httpClientProperties.setOrderUrl("http://localhost:8080/new-orders");
        assertEquals("http://localhost:8080/new-orders", httpClientProperties.getOrderUrl());
    }
}