package com.niti.aws.service;

import com.niti.aws.model.CoinPrice;
import com.niti.aws.repository.CoinPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CoinPriceService {

    private final CoinPriceRepository coinPriceRepository;

    @Autowired
    public CoinPriceService(CoinPriceRepository coinPriceRepository) {
        this.coinPriceRepository = coinPriceRepository;
    }

    public List<CoinPrice> getAllPrices() {
        return coinPriceRepository.findAll();
    }

    public List<CoinPrice> getLatestPrices() {
        return coinPriceRepository.findLatestPrices();
    }

    public List<CoinPrice> getPricesForCoin(String coinId) {
        return coinPriceRepository.findByCoinIdOrderByLastUpdatedDesc(coinId);
    }

    public Optional<CoinPrice> getPriceById(Integer id) {
        return coinPriceRepository.findById(id);
    }

    public List<CoinPrice> getPricesForCoinInDateRange(
            String coinId, LocalDateTime startDate, LocalDateTime endDate) {
        return coinPriceRepository.findByCoinIdAndLastUpdatedBetweenOrderByLastUpdatedDesc(
                coinId, startDate, endDate);
    }

    public List<String> getAvailableCoins() {
        return coinPriceRepository.findDistinctCoinIds();
    }
}