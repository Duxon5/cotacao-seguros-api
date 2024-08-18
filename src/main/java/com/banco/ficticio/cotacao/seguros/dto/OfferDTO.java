package com.banco.ficticio.cotacao.seguros.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OfferDTO(
        String id,
        @JsonProperty("product_id")
        String productId,
        String name,
        @JsonProperty("created_at")
        LocalDateTime createdAt,
        boolean active,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        @JsonProperty("monthly_premium_amount")
        MonthlyPremiumAmountDTO monthlyPremiumAmount
) {

}