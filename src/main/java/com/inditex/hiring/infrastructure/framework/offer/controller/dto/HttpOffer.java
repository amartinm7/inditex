package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;


/**
 * Use this POJO for offer service end point responses.
 */
public record HttpOffer(
        @JsonProperty("offerId") Long offerId,
        @JsonProperty("brandId") Integer brandId,
        @JsonProperty("startDate") String startDate,
        @JsonProperty("endDate") String endDate,
        @JsonProperty("priceListId") Long priceListId,
        @JsonProperty("productPartnumber") String productPartnumber,
        @JsonProperty("priority") Integer priority,
        @JsonProperty("price") BigDecimal price,
        @JsonProperty("currencyIso") String currencyIso
) {
    public static HttpOffer of(Long offerId, Integer brandId, String startDate, String endDate, Long priceListId, String productPartnumber,
                               Integer priority, BigDecimal price, String currencyIso) {
        return new HttpOffer(offerId, brandId, startDate, endDate, priceListId, productPartnumber, priority, price, currencyIso);
    }
}