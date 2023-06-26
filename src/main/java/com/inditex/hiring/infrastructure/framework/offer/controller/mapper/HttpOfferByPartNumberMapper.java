package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class HttpOfferByPartNumberMapper {
    private final OffsetDateTimeHandler offsetDateTimeHandler;

    public HttpOfferByPartNumberMapper(OffsetDateTimeHandler offsetDateTimeHandler) {
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    public HttpOfferByPartNumber mapToHttpResponse(OfferAggregate offerAggregate) {
        return HttpOfferByPartNumber.of(
                offsetDateTimeHandler.toStringUTC(offerAggregate.startDate().value()),
                offsetDateTimeHandler.toStringUTC(offerAggregate.endDate().value()),
                offerAggregate.price().value(),
                offerAggregate.currencyIso().value()
        );
    }

    public List<HttpOfferByPartNumber> mapToHttpResponse(List<OfferAggregate> offerAggregateList) {
        return offerAggregateList
                .stream()
                .map(offer -> mapToHttpResponse(offer))
                .collect(toList());
    }
}
