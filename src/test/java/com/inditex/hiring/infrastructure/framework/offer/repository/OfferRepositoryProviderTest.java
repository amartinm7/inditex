package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_JPA_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_JPA_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_EMPTY;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static com.inditex.hiring.OfferFixtures.ANY_PART_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OfferRepositoryProviderTest {

    private final JpaOfferMapper jpaOfferMapper = mock(JpaOfferMapper.class);
    private final JpaOfferRepositoryClient jpaOfferRepositoryClient = mock(JpaOfferRepositoryClient.class);

    private final OfferRepositoryProvider offerRepositoryProvider =
            new OfferRepositoryProvider(jpaOfferRepositoryClient, jpaOfferMapper);

    @Test
    public void should_return_a_valid_offer_given_any_id() {
        //Given
        mock_repository_find_by_id();
        mock_to_offer_mapper();
        //When
        Offer offer = offerRepositoryProvider.findById(ANY_OFFER_ID);
        //Then
        assertThat(offer).isEqualTo(ANY_OFFER_AGGREGATE);
        verify(jpaOfferRepositoryClient, times(1)).findById(ANY_OFFER_ID);
        verify(jpaOfferMapper, times(1)).toOffer(Optional.of(ANY_JPA_OFFER));
    }

    @Test
    public void should_return_an_empty_for_unknown_offer_id() {
        //Given
        mock_repository_to_unknown_id();
        mock_to_empty_offer_mapper();
        //When
        Offer offer = offerRepositoryProvider.findById(ANY_OFFER_ID);
        //Then
        assertThat(offer).isEqualTo(ANY_OFFER_EMPTY);
        verify(jpaOfferRepositoryClient, times(1)).findById(ANY_OFFER_ID);
        verify(jpaOfferMapper, times(1)).toOffer(Optional.empty());
    }

    @Test
    public void should_return_an_offer_list() {
        //Given
        mock_repository_find_all();
        mock_to_offer_list_mapper();
        //When
        List<OfferAggregate> offerList = offerRepositoryProvider.findAll();
        //Then
        assertThat(offerList).isEqualTo(ANY_ALL_OFFERS);
        verify(jpaOfferRepositoryClient, times(1)).findAll();
        verify(jpaOfferMapper, times(1)).toOffer(ANY_ALL_JPA_OFFERS);
    }

    @Test
    public void should_return_an_offer_list_given_brand_id_and_partNumber() {
        //Given
        mock_repository_find_by_brand_id_and_part_number();
        mock_to_offer_list_mapper();
        //When
        List<OfferAggregate> offerList = offerRepositoryProvider.findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PART_NUMBER);
        //Then
        assertThat(offerList).isEqualTo(ANY_ALL_OFFERS);
        verify(jpaOfferRepositoryClient, times(1))
                .findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PART_NUMBER);
        verify(jpaOfferMapper, times(1)).toOffer(ANY_ALL_JPA_OFFERS);
    }

    @Test
    public void should_delete_an_offer_given_an_offer_id() {
        //Given
        mock_repository_delete_offer_by_id();
        //When
        offerRepositoryProvider.deleteById(ANY_OFFER_ID);
        //Then
        verify(jpaOfferRepositoryClient, times(1)).deleteById(ANY_OFFER_ID);
    }

    private void mock_repository_find_by_id() {
        when(jpaOfferRepositoryClient.findById(ANY_OFFER_ID)).thenReturn(Optional.of(ANY_JPA_OFFER));
    }

    private void mock_repository_find_all() {
        when(jpaOfferRepositoryClient.findAll()).thenReturn(ANY_ALL_JPA_OFFERS);
    }

    private void mock_repository_to_unknown_id() {
        when(jpaOfferRepositoryClient.findById(ANY_OFFER_ID)).thenReturn(Optional.empty());
    }

    private void mock_to_offer_mapper() {
        when(jpaOfferMapper.toOffer(Optional.of(ANY_JPA_OFFER))).thenReturn(ANY_OFFER_AGGREGATE);
    }

    private void mock_to_empty_offer_mapper() {
        when(jpaOfferMapper.toOffer(Optional.empty())).thenReturn(ANY_OFFER_EMPTY);
    }

    private void mock_to_offer_list_mapper() {
        when(jpaOfferMapper.toOffer(ANY_ALL_JPA_OFFERS)).thenReturn(ANY_ALL_OFFERS);
    }

    private void mock_repository_find_by_brand_id_and_part_number() {
        when(jpaOfferRepositoryClient.findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PART_NUMBER))
                .thenReturn(ANY_ALL_JPA_OFFERS);
    }

    private void mock_repository_delete_offer_by_id() {
        doNothing().when(jpaOfferRepositoryClient).deleteById(ANY_OFFER_ID);
    }
}