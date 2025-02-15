package br.com.fiap.ez.fastfood.frameworks.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void testConstructorAndGetter() {
        String testMessage = "Test error message";
        ErrorResponse errorResponse = new ErrorResponse(testMessage);

        assertEquals(testMessage, errorResponse.getMessage());
    }

    @Test
    void testSetter() {
        ErrorResponse errorResponse = new ErrorResponse("Initial message");

        String newMessage = "Updated error message";
        errorResponse.setMessage(newMessage);

        assertEquals(newMessage, errorResponse.getMessage());
    }

    @Test
    void testDefaultConstructorAndSetter() {
        ErrorResponse errorResponse = new ErrorResponse(null);

        String testMessage = "Test error message";
        errorResponse.setMessage(testMessage);

        assertEquals(testMessage, errorResponse.getMessage());
    }
}
