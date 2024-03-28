package org.example.inditex.controller;

import org.example.inditex.entity.Price;
import org.example.inditex.service.IPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    @Autowired
    private IPriceService priceService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Price getPrice(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
                          @RequestParam("productId") long productId,
                          @RequestParam("brandId") int brandId) {
        return priceService.getApplicablePrice(date, productId, brandId);
    }
}
