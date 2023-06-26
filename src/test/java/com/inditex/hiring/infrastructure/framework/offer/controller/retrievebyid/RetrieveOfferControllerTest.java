package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.domain.offer.exception.OfferNotFound;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_HTTP;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE_EMPTY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RetrieveOfferControllerTest {

    private HttpOfferMapper httpOfferMapper = Mockito.mock(HttpOfferMapper.class);
    private RetrieveOfferService retrieveOfferservice = Mockito.mock(RetrieveOfferService.class);
    private RetrieveOfferController retrieveOfferController =
            new RetrieveOfferController(retrieveOfferservice, httpOfferMapper);

    @Test
    void should_retrieve_an_offer() {
        //given
        mock_service_and_retrieve_a_valid_offer();
        mock_httpOfferMapper();
        HttpOffer expectedHttpOffer = ANY_OFFER_HTTP;
        //when
        HttpOffer obtainedHttpOffer = retrieveOfferController.getOfferById(ANY_OFFER_ID);
        //then
        assertThat(obtainedHttpOffer).isEqualTo(expectedHttpOffer);

        verify(retrieveOfferservice, times(1)).execute(ANY_RETRIEVE_OFFER_REQUEST);
        verify(httpOfferMapper, times(1)).mapToHttpResponse(ANY_OFFER_AGGREGATE);
    }

    @Test
    void should_retrieve_an_empty_offer() {
        //given
        mock_service_and_retrieve_an_empty_offer();
        mock_httpOfferMapper();
        //when then
        assertThrows(OfferNotFound.class, () -> {
            retrieveOfferController.getOfferById(ANY_OFFER_ID);
        });
    }

    private void mock_service_and_retrieve_a_valid_offer() {
        Mockito.when(retrieveOfferservice.execute(ANY_RETRIEVE_OFFER_REQUEST))
                .thenReturn(ANY_RETRIEVE_OFFER_RESPONSE);
    }

    private void mock_httpOfferMapper() {
        Mockito.when(httpOfferMapper.mapToHttpResponse(ANY_OFFER_AGGREGATE))
                .thenReturn(ANY_OFFER_HTTP);
    }

    private void mock_service_and_retrieve_an_empty_offer() {
        Mockito.when(retrieveOfferservice.execute(ANY_RETRIEVE_OFFER_REQUEST))
                .thenReturn(ANY_RETRIEVE_OFFER_RESPONSE_EMPTY);
    }
}