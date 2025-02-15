package br.com.fiap.ez.fastfood.frameworks.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {

    private CustomExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new CustomExceptionHandler();
    }

    @Test
    void testHandleIllegalArgumentExceptions() {
        IllegalArgumentException ex = new IllegalArgumentException("Invalid argument");
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleIllegalArgumentExceptions(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid argument", response.getBody().getMessage());
    }

    @Test
    void testHandleBusinessException() {
        BusinessException ex = new BusinessException("Produto não pode ser excluído, pois já faz parte de pedidos.");
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleBusinessException(ex);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Produto não pode ser excluído, pois já faz parte de pedidos.", response.getBody().get("message"));
    }

    @Test
    void testHandleEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");
        ResponseEntity<String> response = exceptionHandler.handleEntityNotFoundException(ex);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Entity not found", response.getBody());
    }
}
