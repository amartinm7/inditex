package com.inditex.hiring.infrastructure.framework.offer.controller.mapper;

import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE_STR;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER_BY_PART_NUMBER_LIST;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE_STR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HttpOfferByPartNumberMapperTest {

    private final OffsetDateTimeHandler offsetDateTimeHandler = mock(OffsetDateTimeHandler.class);
    private final HttpOfferByPartNumberMapper httpOfferByPartNumberMapper = new HttpOfferByPartNumberMapper(offsetDateTimeHandler);

    @Test
    void should_map_a_offer_list() {
        //given
        mock_offsetDateTimeHandler();
        //when
        List<HttpOfferByPartNumber> response = httpOfferByPartNumberMapper.mapToHttpResponse(ANY_ALL_OFFERS);
        //then
        assertThat(response).isEqualTo(ANY_HTTP_OFFER_BY_PART_NUMBER_LIST);
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