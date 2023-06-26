package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

import java.math.BigDecimal;


/**
 * Use this POJO for offer service end point responses.
 */
public record HttpOffer(
        Long offerId,
        Integer brandId,
        String startDate,
        String endDate,
        Long priceListId,
        String productPartnumber,
        Integer priority,
        BigDecimal price,
        String currencyIso
) {
    public static HttpOffer of(Long offerId, Integer brandId, String startDate, String endDate, Long priceListId, String productPartnumber,
                               Integer priority, BigDecimal price, String currencyIso) {
        return new HttpOffer(offerId, brandId, startDate, endDate, priceListId, productPartnumber, priority, price, currencyIso);
    }
}