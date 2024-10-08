package org.example.inditex.controller;

import lombok.RequiredArgsConstructor;
import org.example.inditex.api.PricesApi;
import org.example.inditex.model.Price;
import org.example.inditex.service.IPriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class PriceController implements PricesApi {

    private final IPriceService priceService;

    @Override
    public ResponseEntity<Price> getPrice(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime,
            Integer productId, Integer brandId) {
        Price price = priceService.getApplicablePrice(dateTime, productId, brandId);
        return ResponseEntity.ok(price);
    }
}
