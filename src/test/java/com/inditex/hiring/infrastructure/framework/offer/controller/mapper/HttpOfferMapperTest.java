package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_HTTP_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE_STR;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE_STR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HttpOfferMapperTest {

    private final OffsetDateTimeHandler offsetDateTimeHandler = Mockito.mock(OffsetDateTimeHandler.class);
    private final HttpOfferMapper httpOfferMapper =
            new HttpOfferMapper(offsetDateTimeHandler);

    @Test
    void should_map_an_http_offer_from_an_offer() {
        //given
        mock_offsetDateTimeHandler();
        //when
        HttpOffer response = httpOfferMapper.mapToHttpResponse(ANY_OFFER_AGGREGATE);
        //then
        assertThat(response).isEqualTo(ANY_HTTP_OFFER);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_START_DATE);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_END_DATE);
    }

    @Test
    void should_map_a_http_offer_list_from_an_offer_list() {
        //given
        mock_offsetDateTimeHandler();
        //when
        List<HttpOffer> response = httpOfferMapper.mapToHttpResponse(ANY_ALL_OFFERS);
        //then
        assertThat(response).isEqualTo(ANY_ALL_HTTP_OFFERS);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_START_DATE);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_END_DATE);
    }

    @Test
    void should_map_a_offer_from_a_http_offer() {
        //given
        mock_offsetDateTimeHandler_now();
        //when
        OfferAggregate response = httpOfferMapper.mapToOffer(ANY_HTTP_OFFER);
        //then
        assertThat(response).isEqualTo(ANY_OFFER_AGGREGATE);
        verify(offsetDateTimeHandler, times(2)).now();
        verify(offsetDateTimeHandler, times(1)).toOffsetDateTime(ANY_START_DATE_STR);
        verify(offsetDateTimeHandler, times(1)).toOffsetDateTime(ANY_END_DATE_STR);
    }

    private void mock_offsetDateTimeHandler() {
        Mockito.when(offsetDateTimeHandler.toStringUTC(ANY_START_DATE))
                .thenReturn(ANY_START_DATE_STR);
        Mockito.when(offsetDateTimeHandler.toStringUTC(ANY_END_DATE))
                .thenReturn(ANY_END_DATE_STR);
    }

    private void mock_offsetDateTimeHandler_now() {
        Mockito.when(offsetDateTimeHandler.now())
                .thenReturn(ANY_START_DATE);
        Mockito.when(offsetDateTimeHandler.toOffsetDateTime(ANY_START_DATE_STR))
                .thenReturn(ANY_START_DATE);
        Mockito.when(offsetDateTimeHandler.toOffsetDateTime(ANY_END_DATE_STR))
                .thenReturn(ANY_END_DATE);
    }
}