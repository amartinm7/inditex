package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.Offer;
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
        mock_retrieve_offer_service();
        mock_offsetDateTimeHandler();
        Offer expectedOffer = OfferFixtures.ANY_OFFER;
        //when
        Offer obtainedOffer = retrieveOfferController.getOfferById(OfferFixtures.ANY_OFFER_ID);
        //then
        assertThat(obtainedOffer).isEqualTo(expectedOffer);
    }

    private void mock_retrieve_offer_service() {
        Mockito.when(retrieveOfferservice.execute(OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST))
                .thenReturn(OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE);
    }

    private void mock_offsetDateTimeHandler() {
        Mockito.when(offsetDateTimeHandler.toStringUTC(OfferFixtures.ANY_START_DATE))
                .thenReturn(OfferFixtures.ANY_START_DATE_STR);
    }
}