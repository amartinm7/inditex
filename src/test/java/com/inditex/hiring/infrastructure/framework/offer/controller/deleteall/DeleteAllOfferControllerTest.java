package com.inditex.hiring.infrastructure.framework.offer.controller.deleteall;

import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteAllOfferControllerTest {

    private final DeleteAllOffersService deleteAllOffersService = mock(DeleteAllOffersService.class);
    private final DeleteAllOfferController deleteAllOfferController =
            new DeleteAllOfferController(deleteAllOffersService);

    @Test
    void should_delete_all_offers() {
        //Given
        mock_service_to_delete_all_offers();
        //When
        deleteAllOfferController.deleteAllOffers();
        //then
        verify(deleteAllOffersService, times(1)).execute();
    }

    private void mock_service_to_delete_all_offers() {
        doNothing().when(deleteAllOffersService).execute();
    }
}