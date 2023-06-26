package com.inditex.hiring.domain.offer;

import java.util.List;

public interface OfferRepositoryRead {
    Offer findById(Long offerId);

    List<OfferAggregate> findAll();
}
