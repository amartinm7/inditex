package com.inditex.hiring.application.offer.deletebyid;

import com.inditex.hiring.domain.offer.port.OfferRepository;

public class DeleteOfferService {

    private final OfferRepository offerRepository;
    public DeleteOfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void execute(DeleteOfferServiceRequest request) {
        offerRepository.deleteById(request.offerId());
    }
}
