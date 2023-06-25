package com.inditex.hiring.application.offer.retrievebyid;

import com.inditex.hiring.OfferFixtures;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOfferServiceTest {

    private RetrieveOfferService retrieveOfferService = new RetrieveOfferService();

    @Test
    public void should_return_a_valid_offer_given_any_id() {
        //Given
        RetrieveOfferServiceResponse expectedResponse = ANY_RETRIEVE_OFFER_RESPONSE;
        //when
        RetrieveOfferServiceResponse response = retrieveOfferService.execute(ANY_RETRIEVE_OFFER_REQUEST);
        //then
        assertThat(response).isEqualTo(expectedResponse);
    }
}