package com.inditex.hiring.application.offer.deletebyid;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.domain.offer.port.OfferRepository;
import org.junit.jupiter.api.Test;

import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteOfferServiceTest {

    private final OfferRepository offerRepository = mock(OfferRepository.class);
    private final DeleteOfferService deleteOfferService = new DeleteOfferService(offerRepository);

    @Test
    void should_delete_an_offer() {
        //Given
        mock_repository_to_delete_an_offer_by_id();
        //When
        deleteOfferService.execute(OfferFixtures.ANY_DELETE_OFFER_BY_ID_REQUEST);
        //Then
        verify(offerRepository, times(1)).deleteById(ANY_OFFER_ID);
    }

    private void mock_repository_to_delete_an_offer_by_id() {
        doNothing().when(offerRepository).deleteById(ANY_OFFER_ID);
    }
}