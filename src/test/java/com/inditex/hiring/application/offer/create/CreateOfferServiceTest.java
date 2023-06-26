package com.inditex.hiring.application.offer.create;

import com.inditex.hiring.domain.offer.port.OfferRepository;
import org.junit.jupiter.api.Test;

import static com.inditex.hiring.OfferFixtures.ANY_CREATE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateOfferServiceTest {

    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final CreateOfferService createOfferService = new CreateOfferService(offerRepository);

    @Test
    void should_create_new_offer() {
        //Given
        mock_repository_to_create_a_new_offer();
        //When
        createOfferService.execute(ANY_CREATE_OFFER_REQUEST);
        //Then
        verify(offerRepository, times(1)).save(ANY_OFFER_AGGREGATE);
    }

    private void mock_repository_to_create_a_new_offer() {
        doNothing().when(offerRepository).save(ANY_OFFER_AGGREGATE);
    }
}