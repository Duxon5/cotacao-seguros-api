package com.banco.ficticio.cotacao.seguros.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InsuranceQuoteDTO(
        @JsonProperty("product_id")
        String productId,
        @JsonProperty("offer_id")
        String offerId,
        String category,
        @JsonProperty("total_monthly_premium_amount")
        BigDecimal totalMonthlyPremiumAmount,
        @JsonProperty("total_coverage_amount")
        BigDecimal totalCoverageAmount,
        Map<String, BigDecimal> coverages,
        List<String> assistances,
        CustomerDTO customer
) {

}