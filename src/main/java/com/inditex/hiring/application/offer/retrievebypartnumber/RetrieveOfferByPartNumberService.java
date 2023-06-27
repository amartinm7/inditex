package com.inditex.hiring.application.offer.retrievebypartnumber;

import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.port.OfferRepository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RetrieveOfferByPartNumberService {

    private final OfferRepository offerRepository;

    public RetrieveOfferByPartNumberService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public RetrieveOfferByPartNumberServiceResponse execute(RetrieveOfferByPartNumberServiceRequest request) {
        return new RetrieveOfferByPartNumberServiceResponse(
                addOneSecondToLastInterval(offerRepository.findByBrandIdPartNumber(request.brandId(), request.partNumber()))
        );
    }

    private List<OfferAggregate> addOneSecondToLastInterval(List<OfferAggregate> offerList) {
        List<OfferAggregate> newList = offerList.stream().collect(Collectors.toCollection(ArrayList::new));
        OfferAggregate offerAggregate = newList.get(offerList.size() - 1);
        OffsetDateTime newEndDate = offerAggregate.endDate().value().plusSeconds(1);
        newList.remove(offerList.size() - 1);
        newList.add(offerAggregate.addEndDate(newEndDate));
        return newList;
    }
}
