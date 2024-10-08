package org.example.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void priceNotFoundException_ShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2024-06-14 10:00:00")
                        .param("productId", "99999")
                        .param("brandId", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Price not found"));
    }

    @Test
    public void methodArgumentTypeMismatchException_ShouldReturnBadRequest_Date() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "no-es-una-fecha")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Formato de fecha incorrecto. Usa yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void methodArgumentTypeMismatchException_ShouldReturnBadRequest_Product() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("date", "2020-06-14T10:00:00")
                        .param("productId", "prueba")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Error en el formato del parámetro"));
    }

    @Test
    public void generalException_ShouldReturnInternalServerError() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().is(500));
    }


}

