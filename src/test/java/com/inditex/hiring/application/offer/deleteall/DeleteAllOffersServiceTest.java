package com.inditex.hiring.application.offer.deleteall;


import com.inditex.hiring.domain.offer.port.OfferRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteAllOffersServiceTest {

    private final OfferRepository offerRepository = mock(OfferRepository.class);

    private final DeleteAllOffersService deleteAllOffersService = new DeleteAllOffersService(offerRepository);

    @Test
    public void should_delete_all_offers() {
        //given
        mock_repository_delete_all_offers();
        //When
        deleteAllOffersService.execute();
        //then
        verify(offerRepository, times(1)).deleteAll();
    }

    private void mock_repository_delete_all_offers() {
        doNothing().when(offerRepository).deleteAll();
    }
}