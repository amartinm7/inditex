package com.inditex.hiring.application.offer.retrievebyid;

import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferRepository;

public class RetrieveOfferService {

    private OfferRepository offerRepository;

    public RetrieveOfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public RetrieveOfferServiceResponse execute(RetrieveOfferServiceRequest request) {
        Offer offer = offerRepository.findById(request.offerId());
        return new RetrieveOfferServiceResponse(offer);
    }
}
