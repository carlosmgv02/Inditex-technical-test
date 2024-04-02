package org.example.inditex.repository;

import org.example.inditex.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long>, IPriceRepository {
    @Override
    @Query("SELECT p FROM Price p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN p.startDate AND p.endDate ORDER BY p.priority DESC limit 1")
    Optional<Price> findApplicablePrice(LocalDateTime date, long productId, int brandId);
}