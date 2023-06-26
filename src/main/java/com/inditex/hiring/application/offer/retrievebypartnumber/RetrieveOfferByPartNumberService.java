package com.inditex.hiring.application.offer.retrievebypartnumber;

import com.inditex.hiring.domain.offer.port.OfferRepository;

public class RetrieveOfferByPartNumberService {

    private final OfferRepository offerRepository;

    public RetrieveOfferByPartNumberService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public RetrieveOfferByPartNumberServiceResponse execute(RetrieveOfferByPartNumberServiceRequest request) {
        return new RetrieveOfferByPartNumberServiceResponse(
                offerRepository.findByBrandIdPartNumber(request.brandId(), request.partNumber())
        );
    }
}
