package com.inditex.hiring.application.offer.retrievebypartnumber;

import com.inditex.hiring.domain.offer.OfferAggregate;

import java.util.List;

public record RetrieveOfferByPartNumberServiceResponse(
        List<OfferAggregate> offerList
) {
}
