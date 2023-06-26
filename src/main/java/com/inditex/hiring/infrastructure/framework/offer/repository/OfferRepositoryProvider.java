package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.port.OfferRepository;

import java.util.List;

public class OfferRepositoryProvider implements OfferRepository {

    private final JpaOfferRepositoryClient jpaOfferRepositoryClient;

    private final JpaOfferMapper jpaOfferMapper;

    public OfferRepositoryProvider(JpaOfferRepositoryClient jpaOfferRepositoryClient, JpaOfferMapper jpaOfferMapper) {
        this.jpaOfferRepositoryClient = jpaOfferRepositoryClient;
        this.jpaOfferMapper = jpaOfferMapper;
    }

    @Override
    public Offer findById(Long offerId) {
        return jpaOfferMapper.toOffer(jpaOfferRepositoryClient.findById(offerId));
    }

    @Override
    public List<OfferAggregate> findAll() {
        return jpaOfferMapper.toOffer(jpaOfferRepositoryClient.findAll());
    }

    @Override
    public List<OfferAggregate> findByBrandIdPartNumber(Integer brandId, String partNumber) {
        return jpaOfferMapper.toOffer(jpaOfferRepositoryClient.findByBrandIdPartNumber(brandId, partNumber));
    }

    @Override
    public void deleteById(Long offerId) {
        jpaOfferRepositoryClient.deleteById(offerId);
    }

    @Override
    public void deleteAll() {
        jpaOfferRepositoryClient.deleteAll();
    }
}
