package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.port.OfferRepository;

import java.util.List;

public class OfferRepositoryProvider implements OfferRepository {

    private final JpaOfferRepositoryClient jpaOfferRepositoryClient;

    private final JdbcOfferRepositoryClient jdbcOfferRepositoryClient;

    private final JpaOfferMapper jpaOfferMapper;

    public OfferRepositoryProvider(
            JpaOfferRepositoryClient jpaOfferRepositoryClient,
            JpaOfferMapper jpaOfferMapper,
            JdbcOfferRepositoryClient jdbcOfferRepositoryClient
    ) {
        this.jpaOfferRepositoryClient = jpaOfferRepositoryClient;
        this.jpaOfferMapper = jpaOfferMapper;
        this.jdbcOfferRepositoryClient = jdbcOfferRepositoryClient;
    }

    @Override
    public Offer findById(Long offerId) {
        return jpaOfferMapper.optionalJpaOfferToOffer(jpaOfferRepositoryClient.findById(offerId));
    }

    @Override
    public List<OfferAggregate> findAll() {
        return jpaOfferMapper.toOfferAggregate(jpaOfferRepositoryClient.findAll());
    }

    @Override
    public List<OfferAggregate> findByBrandIdPartNumber(Integer brandId, String partNumber) {
        return jdbcOfferRepositoryClient.findByBrandIdPartNumber(brandId, partNumber);
    }

    @Override
    public void deleteById(Long offerId) {
        jpaOfferRepositoryClient.deleteById(offerId);
    }

    @Override
    public void deleteAll() {
        jpaOfferRepositoryClient.deleteAll();
    }

    @Override
    public void save(OfferAggregate offer) {
        jpaOfferRepositoryClient.save(jpaOfferMapper.toJpaOffer(offer));
    }
}
