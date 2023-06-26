package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.inditex.hiring.OfferFixtures.ANY_END_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE_STR;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_HTTP;
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
    void should_map_an_offer() {
        //given
        mock_offsetDateTimeHandler();
        //when
        HttpOffer response = httpOfferMapper.mapToHttpResponse(ANY_OFFER_AGGREGATE);
        //then
        assertThat(response).isEqualTo(ANY_OFFER_HTTP);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_START_DATE);
        verify(offsetDateTimeHandler, times(1)).toStringUTC(ANY_END_DATE);
    }

    private void mock_offsetDateTimeHandler() {
        Mockito.when(offsetDateTimeHandler.toStringUTC(ANY_START_DATE))
                .thenReturn(ANY_START_DATE_STR);
        Mockito.when(offsetDateTimeHandler.toStringUTC(ANY_END_DATE))
                .thenReturn(ANY_END_DATE_STR);
    }
}