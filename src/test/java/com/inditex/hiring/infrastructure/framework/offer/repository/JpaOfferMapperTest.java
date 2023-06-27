package com.inditex.hiring.infrastructure.framework.offer.repository;


import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_JPA_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_JPA_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

class JpaOfferMapperTest {

    private final JpaOfferMapper jpaOfferMapper = new JpaOfferMapper();

    @Test
    public void should_map_jpa_offer_to_offer() {
        Offer response = jpaOfferMapper.optionalJpaOfferToOffer(Optional.of(ANY_JPA_OFFER));
        assertThat(response).isEqualTo(ANY_OFFER_AGGREGATE);
    }

    @Test
    public void should_map_jpa_offer_to_empty_offer() {
        Offer response = jpaOfferMapper.optionalJpaOfferToOffer(Optional.empty());
        assertThat(response).isEqualTo(ANY_OFFER_EMPTY);
    }

    @Test
    public void should_map_jpa_offer_to_offer_aggregate() {
        OfferAggregate response = jpaOfferMapper.toOfferAggregate(ANY_JPA_OFFER);
        assertThat(response).isEqualTo(ANY_OFFER_AGGREGATE);
    }

    @Test
    public void should_map_a_jpa_offer_list_to_an_offer_aggregate_list() {
        List<OfferAggregate> response = jpaOfferMapper.toOfferAggregate(ANY_ALL_JPA_OFFERS);
        assertThat(response).isEqualTo(ANY_ALL_OFFERS);
    }

    @Test
    public void should_map_an_offer_aggregate_to_a_jpa_offer() {
        JpaOffer response = jpaOfferMapper.toJpaOffer(ANY_OFFER_AGGREGATE);
        assertThat(response).isEqualTo(ANY_JPA_OFFER);
    }
}