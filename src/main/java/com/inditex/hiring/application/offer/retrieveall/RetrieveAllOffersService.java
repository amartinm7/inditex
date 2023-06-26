package com.inditex.hiring.application.offer.retrieveall;

import com.inditex.hiring.domain.offer.OfferRepository;

public class RetrieveAllOffersService {

    private final OfferRepository offerRepository;

    public RetrieveAllOffersService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public RetrieveAllOffersServiceResponse execute() {
        return new RetrieveAllOffersServiceResponse(offerRepository.findAll());
    }
}
