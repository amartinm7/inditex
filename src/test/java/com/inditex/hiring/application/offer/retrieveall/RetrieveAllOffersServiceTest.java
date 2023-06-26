package com.inditex.hiring.application.offer.retrieveall;

import com.inditex.hiring.domain.offer.OfferRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_ALL_OFFERS_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RetrieveAllOffersServiceTest {

    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final RetrieveAllOffersService retrieveAllOffersService =
            new RetrieveAllOffersService(offerRepository);
    @Test
    void should_retrieve_all_offers() {
        //given
        mock_find_all_offers();
        //when
        RetrieveAllOffersServiceResponse response = retrieveAllOffersService.execute();
        //then
        assertThat(response).isEqualTo(ANY_RETRIEVE_ALL_OFFERS_RESPONSE);
        verify(offerRepository, times(1)).findAll();
    }

    private void mock_find_all_offers() {
        Mockito.when(offerRepository.findAll())
                .thenReturn(ANY_ALL_OFFERS);
    }
}