package org.example.inditex.repository;

import org.example.inditex.entity.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface IPriceRepository {
    Optional<Price> findApplicablePrice(LocalDateTime date, long productId, int brandId);
}
