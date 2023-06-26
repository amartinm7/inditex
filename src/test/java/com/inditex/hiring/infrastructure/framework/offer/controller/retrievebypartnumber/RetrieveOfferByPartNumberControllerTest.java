package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebypartnumber;

import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberService;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferByPartNumberMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER_BY_PART_NUMBER_LIST;
import static com.inditex.hiring.OfferFixtures.ANY_PART_NUMBER;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RetrieveOfferByPartNumberControllerTest {
    private final RetrieveOfferByPartNumberService retrieveOfferByPartNumberService =
            mock(RetrieveOfferByPartNumberService.class);
    private final HttpOfferByPartNumberMapper httpOfferPartNumberMapper
            = mock(HttpOfferByPartNumberMapper.class);
    private final RetrieveOfferByPartNumberController retrieveOfferByPartNumberController =
            new RetrieveOfferByPartNumberController(retrieveOfferByPartNumberService, httpOfferPartNumberMapper);

    @Test
    void should_return_a_list_of_offers_by_brand_and_partition() {
        //Given
        mock_service_get_offer_list_by_brand_and_partition();
        mock_map_to_httpOfferPartNumber_list();
        //When
        List<HttpOfferByPartNumber> response =
                retrieveOfferByPartNumberController.getOfferByPartNumber(
                        ANY_BRAND_ID, ANY_PART_NUMBER
                );
        //Then
        assertThat(response).isEqualTo(ANY_HTTP_OFFER_BY_PART_NUMBER_LIST);
        verify(retrieveOfferByPartNumberService, times(1))
                .execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST);
    }

    private void mock_service_get_offer_list_by_brand_and_partition() {
        Mockito.when(retrieveOfferByPartNumberService.execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST))
                .thenReturn(ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE);
    }

    private void mock_map_to_httpOfferPartNumber_list() {
        Mockito.when(httpOfferPartNumberMapper.mapToHttpResponse(ANY_ALL_OFFERS))
                .thenReturn(ANY_HTTP_OFFER_BY_PART_NUMBER_LIST);
    }
}