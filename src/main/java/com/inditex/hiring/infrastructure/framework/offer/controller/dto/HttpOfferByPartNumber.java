package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

import java.math.BigDecimal;

/**
 * Use this POJO on the reponse for brand & partnumber & offer endPoint.
 */
public record HttpOfferByPartNumber(
        String startDate,
        String endDate,
        BigDecimal price,
        String currencyIso
) {
    public static HttpOfferByPartNumber of(String startDate, String endDate, BigDecimal price, String currencyIso) {
        return new HttpOfferByPartNumber(startDate, endDate, price, currencyIso);
    }
}