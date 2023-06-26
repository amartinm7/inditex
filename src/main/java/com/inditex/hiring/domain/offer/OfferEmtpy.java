package com.inditex.hiring.domain.offer;

public record OfferEmtpy() implements Offer {
    public static OfferEmtpy of() {
        return new OfferEmtpy();
    }
}
