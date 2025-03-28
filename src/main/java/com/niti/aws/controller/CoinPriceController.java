package com.niti.aws.controller;

import com.niti.aws.model.CoinPrice;
import com.niti.aws.service.CoinPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/prices")
public class CoinPriceController {

    private final CoinPriceService coinPriceService;

    @Autowired
    public CoinPriceController(CoinPriceService coinPriceService) {
        this.coinPriceService = coinPriceService;
    }

    @GetMapping
    public ResponseEntity<List<CoinPrice>> getAllPrices() {
        return ResponseEntity.ok(coinPriceService.getAllPrices());
    }

    @GetMapping("/latest")
    public ResponseEntity<List<CoinPrice>> getLatestPrices() {
        return ResponseEntity.ok(coinPriceService.getLatestPrices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CoinPrice> getPriceById(@PathVariable Integer id) {
        return coinPriceService.getPriceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/coin/{coinId}")
    public ResponseEntity<List<CoinPrice>> getPricesForCoin(@PathVariable String coinId) {
        return ResponseEntity.ok(coinPriceService.getPricesForCoin(coinId));
    }

    @GetMapping("/coin/{coinId}/range")
    public ResponseEntity<List<CoinPrice>> getPricesForCoinInDateRange(
            @PathVariable String coinId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(coinPriceService.getPricesForCoinInDateRange(coinId, start, end));
    }

    @GetMapping("/coins")
    public ResponseEntity<List<String>> getAvailableCoins() {
        return ResponseEntity.ok(coinPriceService.getAvailableCoins());
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, String>> getApiStatus() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "message", "Crypto Price API is running",
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}