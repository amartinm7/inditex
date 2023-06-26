package com.inditex.hiring.application.offer.create;

import com.inditex.hiring.domain.offer.port.OfferRepository;

public class CreateOfferService {

    private final OfferRepository offerRepository;

    public CreateOfferService(OfferRepository offerRepository) {

        this.offerRepository = offerRepository;
    }

    public void execute(CreateOfferServiceRequest request){
        offerRepository.save(request.offer());
    }
}
