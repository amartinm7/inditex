package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.OfferEmtpy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static org.assertj.core.api.Assertions.assertThat;

class OfferRepositoryProviderTest {

    private JpaOfferRepositoryClient jpaOfferRepositoryClient = Mockito.mock(JpaOfferRepositoryClient.class);

    private OfferRepositoryProvider offerRepositoryProvider = new OfferRepositoryProvider(jpaOfferRepositoryClient);

    @Test
    public void should_return_a_valid_offer_given_any_id() {
        //Given
        mock_repository();
        OfferAggregate expectedOffer = OfferFixtures.ANY_OFFER_AGGREGATE;
        //When
        Offer offer = offerRepositoryProvider.findById(ANY_OFFER_ID);
        //Then
        assertThat(offer).isEqualTo(expectedOffer);
    }

    @Test
    public void should_return_an_empty_for_unknown_offer_id() {
        //Given
        mock_repository_to_unknown_id();
        OfferEmtpy expectedOffer = OfferFixtures.ANY_OFFER_EMPTY;
        //When
        Offer offer = offerRepositoryProvider.findById(ANY_OFFER_ID);
        //Then
        assertThat(offer).isEqualTo(expectedOffer);
    }

    private void mock_repository() {
        Mockito.when(jpaOfferRepositoryClient.findById(ANY_OFFER_ID))
                .thenReturn(Optional.of(OfferFixtures.ANY_JPA_OFFER));
    }

    private void mock_repository_to_unknown_id() {
        Mockito.when(jpaOfferRepositoryClient.findById(ANY_OFFER_ID))
                .thenReturn(Optional.empty());
    }
}