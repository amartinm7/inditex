package com.inditex.hiring.application.offer.retrievebypartnumber;

import com.inditex.hiring.domain.offer.port.OfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_PRODUCT_PART_NUMBER;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_ALL_OFFERS_RESPONSE;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RetrieveOfferByPartNumberServiceTest {

    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final RetrieveOfferByPartNumberService retrieveOfferByPartNumberService =
            new RetrieveOfferByPartNumberService(offerRepository);

    @Test
    void should_retrieve_all_offers() {
        //given
        mock_find_all_offers_by_brandId_and_part_number();
        //when
        RetrieveOfferByPartNumberServiceResponse response =
                retrieveOfferByPartNumberService.execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST);
        //then
        assertThat(response).isEqualTo(ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE);
        verify(offerRepository, times(1))
                .findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PRODUCT_PART_NUMBER);
    }

    private void mock_find_all_offers_by_brandId_and_part_number() {
        Mockito.when(offerRepository.findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PRODUCT_PART_NUMBER))
                .thenReturn(ANY_ALL_OFFERS);
    }

}