package com.inditex.hiring.domain.offer.port;

public interface OfferRepositoryWrite {
    void deleteById(Long offerId);

    void deleteAll();
}
