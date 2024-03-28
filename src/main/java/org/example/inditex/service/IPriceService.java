package org.example.inditex.service;

import org.example.inditex.entity.Price;

import java.time.LocalDateTime;

public interface IPriceService {
    Price getApplicablePrice(LocalDateTime applicationDate, long productId, int brandId);
}
