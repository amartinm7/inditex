package com.inditex.hiring.domain.offer;

public record OfferAggregate(
        OfferId offerId,
        BrandId brandId,
        StartDate startDate,
        EndDate endDate,
        PriceListId priceListId,
        ProductPartNumber productPartnumber,
        Priority priority,
        Price price,
        CurrencyIso currencyIso
) {
    public static OfferAggregate of(OfferId offerId, BrandId brandId, StartDate startDate, EndDate endDate, PriceListId priceListId, ProductPartNumber productPartnumber, Priority priority, Price price, CurrencyIso currencyIso) {
        return new OfferAggregate(offerId, brandId, startDate, endDate, priceListId, productPartnumber, priority, price, currencyIso);
    }
}