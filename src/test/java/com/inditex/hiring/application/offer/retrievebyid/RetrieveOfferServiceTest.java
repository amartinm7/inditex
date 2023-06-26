package com.inditex.hiring.application.offer.retrievebyid;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.domain.offer.port.OfferRepository;
import com.inditex.hiring.infrastructure.framework.offer.repository.OfferRepositoryProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE_EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RetrieveOfferServiceTest {

    private final OfferRepository offerRepository = Mockito.mock(OfferRepositoryProvider.class);
    private final RetrieveOfferService retrieveOfferService = new RetrieveOfferService(offerRepository);

    @Test
    public void should_return_a_valid_offer_given_any_id() {
        //Given
        mock_repository();
        RetrieveOfferServiceResponse expectedResponse = ANY_RETRIEVE_OFFER_RESPONSE;
        //when
        RetrieveOfferServiceResponse response = retrieveOfferService.execute(ANY_RETRIEVE_OFFER_REQUEST);
        //then
        assertThat(response).isEqualTo(expectedResponse);
        verify(offerRepository,times(1)).findById(OfferFixtures.ANY_OFFER_ID);
    }

    @Test
    public void should_return_an_empty_offer_if_not_exists() {
        //Given
        mock_repository_for_offer_not_found();
        RetrieveOfferServiceResponse expectedResponse = ANY_RETRIEVE_OFFER_RESPONSE_EMPTY;
        //when
        RetrieveOfferServiceResponse response = retrieveOfferService.execute(ANY_RETRIEVE_OFFER_REQUEST);
        //then
        assertThat(response).isEqualTo(expectedResponse);
        verify(offerRepository,times(1)).findById(OfferFixtures.ANY_OFFER_ID);
    }

    private void mock_repository() {
        Mockito.when(offerRepository.findById(OfferFixtures.ANY_OFFER_ID))
                .thenReturn(OfferFixtures.ANY_OFFER_AGGREGATE);
    }

    private void mock_repository_for_offer_not_found() {
        Mockito.when(offerRepository.findById(OfferFixtures.ANY_OFFER_ID))
                .thenReturn(OfferFixtures.ANY_OFFER_EMPTY);
    }
}