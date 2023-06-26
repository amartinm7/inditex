package com.inditex.hiring.application.offer.deleteall;

import com.inditex.hiring.domain.offer.port.OfferRepository;

public class DeleteAllOffersService {
    private final OfferRepository offerRepository;

    public DeleteAllOffersService(OfferRepository offerRepository) {

        this.offerRepository = offerRepository;
    }

    public void execute() {
        offerRepository.deleteAll();
    }
}
