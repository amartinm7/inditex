package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.domain.offer.BrandId;
import com.inditex.hiring.domain.offer.CreatedAt;
import com.inditex.hiring.domain.offer.CurrencyIso;
import com.inditex.hiring.domain.offer.EndDate;
import com.inditex.hiring.domain.offer.ModifiedAt;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.OfferId;
import com.inditex.hiring.domain.offer.PartNumber;
import com.inditex.hiring.domain.offer.Price;
import com.inditex.hiring.domain.offer.PriceListId;
import com.inditex.hiring.domain.offer.Priority;
import com.inditex.hiring.domain.offer.StartDate;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class HttpOfferMapper {

    private OffsetDateTimeHandler offsetDateTimeHandler;

    public HttpOfferMapper(OffsetDateTimeHandler offsetDateTimeHandler) {
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    public HttpOffer mapToHttpResponse(OfferAggregate offerAggregate) {
        return HttpOffer.of(
                offerAggregate.offerId().value(),
                offerAggregate.brandId().value(),
                offsetDateTimeHandler.toStringUTC(offerAggregate.startDate().value()),
                offsetDateTimeHandler.toStringUTC(offerAggregate.endDate().value()),
                offerAggregate.priceListId().value(),
                offerAggregate.partnumber().value(),
                offerAggregate.priority().value(),
                offerAggregate.price().value(),
                offerAggregate.currencyIso().value()
        );
    }

    public List<HttpOffer> mapToHttpResponse(List<OfferAggregate> offerAggregateList) {
       return offerAggregateList
               .stream()
               .map(offer -> mapToHttpResponse(offer))
               .collect(toList());
    }

    public OfferAggregate mapToOffer(HttpOffer httpOffer) {
        return new OfferAggregate(
                new OfferId(httpOffer.offerId()),
                new BrandId(httpOffer.brandId()),
                new StartDate(offsetDateTimeHandler.toOffsetDateTime(httpOffer.startDate())),
                new EndDate(offsetDateTimeHandler.toOffsetDateTime(httpOffer.endDate())),
                new PriceListId(httpOffer.priceListId()),
                new PartNumber(httpOffer.productPartnumber()),
                new Priority(httpOffer.priority()),
                new Price(httpOffer.price()),
                new CurrencyIso(httpOffer.currencyIso()),
                new CreatedAt(offsetDateTimeHandler.now()),
                new ModifiedAt(offsetDateTimeHandler.now())
        );
    }

}
