package org.example.inditex;


import org.example.inditex.exception.PriceNotFoundException;
import org.example.inditex.model.Price;
import org.example.inditex.repository.IPriceRepository;
import org.example.inditex.service.IPriceService;
import org.example.inditex.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private IPriceService priceService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        priceService = new PriceService(priceRepository);
    }

    @Test
    void getApplicablePrice_ShouldReturnPrice() {
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        int brandId = 1;
        org.example.inditex.entity.Price expectedPrice =
                new org.example.inditex.entity.Price(1L, brandId, applicationDate, applicationDate, 1, productId, 0,
                        new BigDecimal("100.00"),
                        "EUR");
        when(priceRepository.findApplicablePrice(applicationDate, productId, brandId)).thenReturn(
                Optional.of(expectedPrice));

        Price result = priceService.getApplicablePrice(applicationDate, productId, brandId);

        assertNotNull(result);
        pricesEquals(expectedPrice, result);
    }

    @Test
    void getApplicablePrice_WhenPriceNotFound_ShouldThrowException() {
        LocalDateTime applicationDate = LocalDateTime.now();
        long productId = 1L;
        int brandId = 1;
        when(priceRepository.findApplicablePrice(any(LocalDateTime.class), any(Long.class),
                any(Integer.class))).thenReturn(Optional.empty());

        assertThrows(PriceNotFoundException.class,
                () -> priceService.getApplicablePrice(applicationDate, productId, brandId));
    }

    private void pricesEquals(org.example.inditex.entity.Price expectedPrice, Price result) {
        assertNotNull(expectedPrice);
        assertNotNull(result);

        // Comparar el ID
        assertEquals(expectedPrice.getId(), result.getId(), "Brand ID mismatch");

        // Comparar el brandId
        assertEquals(expectedPrice.getBrandId(), result.getBrandId(), "Brand ID mismatch");

        // Comparar la fecha de inicio (startDate)
        assertEquals(expectedPrice.getStartDate(), result.getStartDate(), "Start date mismatch");

        // Comparar la fecha de finalización (endDate)
        assertEquals(expectedPrice.getEndDate(), result.getEndDate(), "End date mismatch");

        // Comparar el priceList
        assertEquals(expectedPrice.getPriceList(), result.getPriceList(), "Price list mismatch");

        // Comparar el productId
        assertEquals(expectedPrice.getProductId(), result.getProductId(), "Product ID mismatch");

        // Comparar el priority
        assertEquals(expectedPrice.getPriority(), result.getPriority(), "Priority mismatch");

        // Comparar el precio
        assertEquals(expectedPrice.getPrice(), result.getPrice(), "Price mismatch");

        // Comparar la moneda (currency)
        assertEquals(expectedPrice.getCurr(), result.getCurr(), "Currency mismatch");
    }
}

