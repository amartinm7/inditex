package com.inditex.hiring.domain.offer.port;

import com.inditex.hiring.domain.offer.OfferAggregate;

public interface OfferRepositoryWrite {
    void deleteById(Long offerId);

    void deleteAll();

    void save(OfferAggregate offer);
}
