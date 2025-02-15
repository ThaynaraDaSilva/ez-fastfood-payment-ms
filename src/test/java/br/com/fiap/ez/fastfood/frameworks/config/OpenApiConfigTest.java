//package br.com.fiap.ez.fastfood.frameworks.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class OpenApiConfigTest {
//
//    @Autowired
//    private OpenApiConfig openApiConfig;
//
//    @Test
//    void testCustomOpenApi() {
//        OpenAPI openAPI = openApiConfig.customOpenApi();
//
//        Info info = openAPI.getInfo();
//        assertEquals("PAYMENT MS", info.getTitle());
//        assertEquals("ez-fastfood-payment-ms", info.getDescription());
//        assertEquals("1.0", info.getVersion());
//    }
//}
