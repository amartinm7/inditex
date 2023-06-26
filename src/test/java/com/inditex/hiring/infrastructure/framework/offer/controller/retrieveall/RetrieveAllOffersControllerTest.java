package com.inditex.hiring.infrastructure.framework.offer.controller.retrieveall;

import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_HTTP_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_ALL_OFFERS_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

class RetrieveAllOffersControllerTest {
    private final HttpOfferMapper httpOfferMapper = Mockito.mock(HttpOfferMapper.class);
    private RetrieveAllOffersService retrieveAllOffersService = Mockito.mock(RetrieveAllOffersService.class);
    private RetrieveAllOffersController retrieveAllOffersController =
            new RetrieveAllOffersController(retrieveAllOffersService, httpOfferMapper);

    @Test
    void should_retrieve_all_offers() {
        //given
        mock_service_to_retrieve_all_offers();
        mock_map_to_httpOffer_list();
        //when
        List<HttpOffer> response = retrieveAllOffersController.getAllOffers();
        //then
        assertThat(response.size()).isEqualTo(1);

        verify(retrieveAllOffersService).execute();
    }

    private void mock_service_to_retrieve_all_offers() {
        Mockito.when(retrieveAllOffersService.execute()).thenReturn(ANY_RETRIEVE_ALL_OFFERS_RESPONSE);
    }

    private void mock_map_to_httpOffer_list() {
        Mockito.when(httpOfferMapper.mapToHttpResponse(ANY_ALL_OFFERS))
                .thenReturn(ANY_ALL_HTTP_OFFERS);
    }
}