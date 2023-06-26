package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.domain.offer.exception.OfferNotFound;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOfferControllerTest {

    private OffsetDateTimeHandler offsetDateTimeHandler = Mockito.mock(OffsetDateTimeHandler.class);
    private RetrieveOfferService retrieveOfferservice = Mockito.mock(RetrieveOfferService.class);
    private RetrieveOfferController retrieveOfferController = new RetrieveOfferController(retrieveOfferservice, offsetDateTimeHandler);

    @Test
    void should_retrieve_an_offer() {
        //given
        mock_service_and_retrieve_a_valid_offer();
        mock_offsetDateTimeHandler();
        HttpOffer expectedHttpOffer = OfferFixtures.ANY_OFFER_HTTP;
        //when
        HttpOffer obtainedHttpOffer = retrieveOfferController.getOfferById(OfferFixtures.ANY_OFFER_ID);
        //then
        assertThat(obtainedHttpOffer).isEqualTo(expectedHttpOffer);
    }

    @Test
    void should_retrieve_an_empty_offer() {
        //given
        mock_service_and_retrieve_an_empty_offer();
        mock_offsetDateTimeHandler();
        //when then
        org.junit.jupiter.api.Assertions.assertThrows(OfferNotFound.class, () -> {
            retrieveOfferController.getOfferById(OfferFixtures.ANY_OFFER_ID);
        });
    }

    private void mock_service_and_retrieve_a_valid_offer() {
        Mockito.when(retrieveOfferservice.execute(OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST))
                .thenReturn(OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE);
    }

    private void mock_offsetDateTimeHandler() {
        Mockito.when(offsetDateTimeHandler.toStringUTC(OfferFixtures.ANY_START_DATE))
                .thenReturn(OfferFixtures.ANY_START_DATE_STR);
    }

    private void mock_service_and_retrieve_an_empty_offer() {
        Mockito.when(retrieveOfferservice.execute(OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST))
                .thenReturn(OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE_EMPTY);
    }
}