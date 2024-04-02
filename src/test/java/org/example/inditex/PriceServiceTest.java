package org.example.inditex;


import org.example.inditex.entity.Price;
import org.example.inditex.exception.PriceNotFoundException;
import org.example.inditex.repository.IPriceRepository;
import org.example.inditex.service.PriceService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PriceServiceTest {

    private final IPriceRepository priceRepository = Mockito.mock(IPriceRepository.class);

    @InjectMocks
    private PriceService priceService;

    public PriceServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getApplicablePrice_ShouldReturnPrice() {
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        int brandId = 1;
        Price expectedPrice = new Price(1L, brandId, applicationDate, applicationDate, 1, productId, 0, new BigDecimal("100.00"), "EUR");
        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(Optional.of(expectedPrice));

        Price result = priceService.getApplicablePrice(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(expectedPrice, result);
    }

    @Test
    void getApplicablePrice_WhenPriceNotFound_ShouldThrowException() {
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        int brandId = 1;
        when(priceRepository.findApplicablePrice(any(LocalDateTime.class), any(Long.class), any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class, () -> priceService.getApplicablePrice(applicationDate, productId, brandId));
    }
}

