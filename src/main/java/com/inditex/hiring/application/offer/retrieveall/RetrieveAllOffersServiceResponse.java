package com.inditex.hiring.application.offer.retrieveall;

import com.inditex.hiring.domain.offer.OfferAggregate;

import java.util.List;

public record RetrieveAllOffersServiceResponse(List<OfferAggregate> offerList) {
}
