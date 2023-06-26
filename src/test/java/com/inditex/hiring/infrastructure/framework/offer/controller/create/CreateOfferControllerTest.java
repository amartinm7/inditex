package com.inditex.hiring.infrastructure.framework.offer.controller.create;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import org.junit.jupiter.api.Test;

import static com.inditex.hiring.OfferFixtures.ANY_CREATE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateOfferControllerTest {
    private final CreateOfferService createOfferService = mock(CreateOfferService.class);

    private final HttpOfferMapper httpOfferMapper = mock(HttpOfferMapper.class);

    private final CreateOfferController createOfferController =
            new CreateOfferController(createOfferService, httpOfferMapper);
    @Test
    void should_create_a_new_offer() {
        //Given
        mock_service_to_create_an_offer();
        mock_mapper_to_pass_a_http_offer_to_an_offer();
        //When
        createOfferController.createNewOffer(ANY_HTTP_OFFER);
        //Then
        verify(createOfferService, times(1)).execute(ANY_CREATE_OFFER_REQUEST);
        verify(httpOfferMapper, times(1)).mapToOffer(ANY_HTTP_OFFER);
    }

    private void mock_service_to_create_an_offer() {
        doNothing().when(createOfferService).execute(ANY_CREATE_OFFER_REQUEST);
    }

    private void mock_mapper_to_pass_a_http_offer_to_an_offer() {
        when(httpOfferMapper.mapToOffer(ANY_HTTP_OFFER)).thenReturn(ANY_OFFER_AGGREGATE);
    }
}