package com.niti.aws.repository;

import com.niti.aws.model.CoinPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CoinPriceRepository extends JpaRepository<CoinPrice, Integer> {

    // Find all prices for a specific coin, sorted by newest first
    List<CoinPrice> findByCoinIdOrderByLastUpdatedDesc(String coinId);

    // Get latest price for each coin
    @Query("SELECT cp FROM CoinPrice cp WHERE cp.lastUpdated = " +
            "(SELECT MAX(cp2.lastUpdated) FROM CoinPrice cp2 WHERE cp2.coinId = cp.coinId)")
    List<CoinPrice> findLatestPrices();

    // Get prices for a specific coin within a date range
    List<CoinPrice> findByCoinIdAndLastUpdatedBetweenOrderByLastUpdatedDesc(
            String coinId, LocalDateTime startDate, LocalDateTime endDate);

    // Find distinct coin IDs
    @Query("SELECT DISTINCT cp.coinId FROM CoinPrice cp ORDER BY cp.coinId")
    List<String> findDistinctCoinIds();
}
