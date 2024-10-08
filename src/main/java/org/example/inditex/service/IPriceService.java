package org.example.inditex.service;


import org.example.inditex.model.Price;

import java.time.LocalDateTime;

public interface IPriceService {
    Price getApplicablePrice(LocalDateTime applicationDate, long productId, int brandId);
}
