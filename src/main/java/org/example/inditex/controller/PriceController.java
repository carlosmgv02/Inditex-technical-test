package org.example.inditex.controller;

import lombok.RequiredArgsConstructor;
import org.example.inditex.entity.Price;
import org.example.inditex.service.IPriceService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final IPriceService priceService;

    @GetMapping
    public ResponseEntity<Price> getPrice(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                                         @RequestParam("productId") long productId,
                                         @RequestParam("brandId") int brandId) {
        return ResponseEntity.ok(priceService.getApplicablePrice(date, productId, brandId));
    }
}
