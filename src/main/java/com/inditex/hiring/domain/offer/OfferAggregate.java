package com.inditex.hiring.domain.offer;

import java.time.OffsetDateTime;

public record OfferAggregate(
        OfferId offerId,
        BrandId brandId,
        StartDate startDate,
        EndDate endDate,
        PriceListId priceListId,
        PartNumber partnumber,
        Priority priority,
        Price price,
        CurrencyIso currencyIso,
        CreatedAt createdAt,
        ModifiedAt modifiedAt

) implements Offer {
    public static OfferAggregate of(OfferId offerId, BrandId brandId, StartDate startDate, EndDate endDate,
                                    PriceListId priceListId, PartNumber partnumber, Priority priority,
                                    Price price, CurrencyIso currencyIso, CreatedAt createdAt, ModifiedAt modifiedAt) {
        return new OfferAggregate(offerId, brandId, startDate, endDate, priceListId, partnumber, priority,
                price, currencyIso, createdAt, modifiedAt);
    }

    public OfferAggregate addEndDate(OffsetDateTime newEndDate) {
        return OfferAggregate.of(offerId, brandId, startDate, new EndDate(newEndDate), priceListId, partnumber,
                priority, price, currencyIso, createdAt, modifiedAt);
    }
}