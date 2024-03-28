package org.example.inditex.service;

import org.example.inditex.entity.Price;
import org.example.inditex.exception.PriceNotFoundException;
import org.example.inditex.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PriceService implements IPriceService{

    @Autowired
    private PriceRepository priceRepository;

    public Price getApplicablePrice(LocalDateTime applicationDate, long productId, int brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId).stream().findFirst()
                       .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}