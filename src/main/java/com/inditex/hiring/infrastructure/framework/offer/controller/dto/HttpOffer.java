package com.inditex.hiring.infrastructure.framework.offer.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.inditex.hiring.infrastructure.framework.config.DateFormatConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


/**
 * Use this POJO for offer service end point responses.
 */
public record HttpOffer(
    @JsonProperty("offerId")
    @NotNull(message = "offerId cannot be blank")
    @Max(value = 999999999)
    Long offerId,
    @JsonProperty("brandId")
    @NotNull(message = "brandId cannot be blank")
    @Max(value = 999999999)
    Integer brandId,
    @JsonProperty("startDate")
    @NotNull(message = "startDate cannot be blank")
    @NotEmpty(message = "startDate cannot be empty")
    @DateFormatConstraint(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    String startDate,
    @JsonProperty("endDate")
    @NotNull(message = "endDate cannot be blank")
    @NotEmpty(message = "endDate cannot be empty")
    @DateFormatConstraint(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    String endDate,
    @JsonProperty("priceListId")
    @NotNull(message = "priceListId cannot be blank")
    @Max(value = 999999999)
    Long priceListId,
    @JsonProperty("productPartnumber")
    @NotNull(message = "productPartnumber cannot be blank")
    @NotEmpty(message = "productPartnumber cannot be empty")
    String productPartnumber,
    @JsonProperty("priority")
    @NotNull(message = "Integer cannot be blank")
    @Max(value = 999999999)
    Integer priority,
    @JsonProperty("price")
    @NotNull(message = "price cannot be blank")
    @Max(value = 999999999)
    BigDecimal price,
    @JsonProperty("currencyIso")
    @NotNull(message = "currencyIso cannot be blank")
    @NotEmpty(message = "currencyIso cannot be empty")
    String currencyIso
) {
    public static HttpOffer of(Long offerId, Integer brandId, String startDate, String endDate, Long priceListId, String productPartnumber,
                               Integer priority, BigDecimal price, String currencyIso) {
        return new HttpOffer(offerId, brandId, startDate, endDate, priceListId, productPartnumber, priority, price, currencyIso);
    }
}
