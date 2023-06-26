package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.domain.offer.OfferAggregate;
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
}
