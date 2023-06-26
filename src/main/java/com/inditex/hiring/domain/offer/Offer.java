package com.inditex.hiring.domain.offer;

public sealed interface Offer permits OfferAggregate, OfferEmtpy {
}
