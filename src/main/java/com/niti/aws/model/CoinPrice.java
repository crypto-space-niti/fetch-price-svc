package com.niti.aws.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "coin_prices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinPrice {

    @Id
    private Integer id;

    @Column(name = "coin_id")
    private String coinId;

    @Column(name = "price_usd")
    private BigDecimal priceUsd;

    @Column(name = "market_cap")
    private BigDecimal marketCap;

    @Column(name = "volume_24h")
    private BigDecimal volume24h;

    @Column(name = "price_change_24h")
    private BigDecimal priceChange24h;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;
}