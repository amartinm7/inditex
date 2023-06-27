package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Use this POJO on the reponse for brand & partnumber & offer endPoint.
 */
public record HttpOfferByPartNumber(
        @JsonProperty("startDate") String startDate,
        @JsonProperty("endDate") String endDate,
        @JsonProperty("price") BigDecimal price,
        @JsonProperty("currencyIso") String currencyIso
) {
    public static HttpOfferByPartNumber of(String startDate, String endDate, BigDecimal price, String currencyIso) {
        return new HttpOfferByPartNumber(startDate, endDate, price, currencyIso);
    }
}