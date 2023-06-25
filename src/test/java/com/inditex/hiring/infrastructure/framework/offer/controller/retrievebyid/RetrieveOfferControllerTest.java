package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.Offer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOfferControllerTest {

    RetrieveOfferController retrieveOfferController = new RetrieveOfferController();

    @Test
    void should_retrieve_an_offer() {
        Offer expectedOffer = new Offer();
        Offer obtainedOffer = retrieveOfferController.getOfferById(OfferFixtures.ANY_OFFER_ID);
        assertThat(obtainedOffer).isEqualTo(expectedOffer);
    }
}