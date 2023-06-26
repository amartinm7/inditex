package com.inditex.hiring.domain.offer.exception;

public class OfferNotFound extends RuntimeException{

    public OfferNotFound(Long id) {
        super( "Offer not found for id: " +  id);
    }
}
