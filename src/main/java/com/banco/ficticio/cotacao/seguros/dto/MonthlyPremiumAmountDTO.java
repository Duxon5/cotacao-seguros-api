package com.banco.ficticio.cotacao.seguros.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MonthlyPremiumAmountDTO(
        @JsonProperty("max_amount")
        BigDecimal maxAmount,
        @JsonProperty("min_amount")
        BigDecimal minAmount,
        @JsonProperty("suggested_amount")
        BigDecimal suggestedAmount
) {

}