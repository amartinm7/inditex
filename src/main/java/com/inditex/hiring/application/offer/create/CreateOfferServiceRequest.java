package com.inditex.hiring.application.offer.create;

import com.inditex.hiring.domain.offer.OfferAggregate;

public record CreateOfferServiceRequest(OfferAggregate offer) {
}
