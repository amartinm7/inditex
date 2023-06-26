package com.inditex.hiring.domain.offer.port;

import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;

import java.util.List;

public interface OfferRepositoryRead {
    Offer findById(Long offerId);

    List<OfferAggregate> findAll();

    List<OfferAggregate> findByBrandIdPartNumber(Integer anyBrandId, String anyProductPartNumber);
}
