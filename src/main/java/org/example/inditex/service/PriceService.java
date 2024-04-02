package org.example.inditex.service;

import lombok.RequiredArgsConstructor;
import org.example.inditex.entity.Price;
import org.example.inditex.exception.PriceNotFoundException;
import org.example.inditex.repository.IPriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements IPriceService{

    private final IPriceRepository priceRepository;

    public Price getApplicablePrice(LocalDateTime applicationDate, long productId, int brandId) {
        return priceRepository.findApplicablePrice(applicationDate, productId, brandId)
                       .orElseThrow(() -> new PriceNotFoundException("Price not found"));
    }
}