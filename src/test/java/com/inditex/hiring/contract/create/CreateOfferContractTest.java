package com.inditex.hiring.contract.create;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.contract.SpringbootContractTest;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static com.inditex.hiring.OfferFixtures.ANY_CREATE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_END_DATE_STR;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE_STR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateOfferContractTest extends SpringbootContractTest {

    @Autowired
    private CreateOfferService createOfferService;

    @Autowired
    private OffsetDateTimeHandler offsetDateTimeHandler;

    @Test
    void should_create_an_offer() throws Exception {
        //Given
        mock_service_to_create_an_offer();
        mock_dates();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
                .andExpect(status().isCreated());

        verify(createOfferService, times(1)).execute(ANY_CREATE_OFFER_REQUEST);
        verify(offsetDateTimeHandler, times(2)).now();
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_return_bad_request(String testName, String context) throws Exception {
        assertThat(testName).isNotEmpty();
        //When / then
        mockMvc.perform(newHttpRequestBadRequest(context))
                .andExpect(status().isBadRequest());

        verify(createOfferService, times(0)).execute(any());
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null currencyIso","{\"brandId\": 1,\"currencyIso\": ,\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty currencyIso","{\"brandId\": 1,\"currencyIso\": \"\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null endDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": ,\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty endDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Invalid type endDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"3243243\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Invalid format endDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31 23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null offerId","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": ,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Max offerId","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 9999999999,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null price","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": ,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty price","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": \"\",\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Max price","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 9999999999,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null priceListId","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": ,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty priceListId","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": \"\",\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Max priceListId","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 9999999999,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null priority","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": ,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty priority","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": \"\",\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Max priority","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 9999999999,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null productPartnumber","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": ,\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Empty productPartnumber","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
                Arguments.of("Null startDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": ,\"startDate\": }"),
                Arguments.of("Empty startDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"\",\"startDate\": \"\"}"),
                Arguments.of("Invalid type startDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": ,\"startDate\":  \"3243\"}"),
                Arguments.of("Invalid format startDate","{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"\",\"startDate\": \"2020-06-14 00:00:00\"}")
        );
    }

    private RequestBuilder newHttpRequestBadRequest(String content) {
        return MockMvcRequestBuilders.post("/offer")
                .content(content)
                .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.post("/offer")
                .content("{\"brandId\": 1,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
                .contentType("application/json");
    }

    private void mock_service_to_create_an_offer() {
        doNothing().when(createOfferService).execute(ANY_CREATE_OFFER_REQUEST);
    }

    private void mock_dates() {
        when(offsetDateTimeHandler.now()).thenReturn(ANY_START_DATE);
        when(offsetDateTimeHandler.toOffsetDateTime(ANY_START_DATE_STR)).thenReturn(ANY_START_DATE);
        when(offsetDateTimeHandler.toOffsetDateTime(ANY_END_DATE_STR)).thenReturn(ANY_END_DATE);
    }
}
