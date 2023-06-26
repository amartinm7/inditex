package com.inditex.hiring.infrastructure.framework.offer.controller.deletebyid;

import com.inditex.hiring.application.offer.deletebyid.DeleteOfferService;
import org.junit.jupiter.api.Test;

import static com.inditex.hiring.OfferFixtures.ANY_DELETE_OFFER_BY_ID_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class DeleteOfferControllerTest {

    private final DeleteOfferService deleteOfferService = mock(DeleteOfferService.class);
    private DeleteOfferController deleteOfferController = new DeleteOfferController(deleteOfferService);

    @Test
    void should_delete_an_offer() {
        //Given
        mock_service_to_delete_an_offer();
        //When
        deleteOfferController.deleteOfferById(ANY_OFFER_ID);
        //Then
        verify(deleteOfferService, times(1)).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }

    private void mock_service_to_delete_an_offer() {
        doNothing().when(deleteOfferService).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }
}