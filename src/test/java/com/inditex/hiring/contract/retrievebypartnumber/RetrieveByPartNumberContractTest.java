package com.inditex.hiring.contract.retrievebypartnumber;

import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberService;
import com.inditex.hiring.contract.SpringbootContractTest;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferByPartNumberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER_BY_PART_NUMBER_LIST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveByPartNumberContractTest extends SpringbootContractTest {

    @Autowired
    private RetrieveOfferByPartNumberService retrieveOfferByPartNumberService;

    @Autowired
    private HttpOfferByPartNumberMapper httpOfferPartNumberMapper;

    @Test
    void should_retrieve_time_interval_for_offers() throws Exception {
        //Given
        mock_service_get_offer_list_by_brand_and_partition();
        mock_map_to_httpOfferPartNumber_list();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(expectedJson, true));

        verify(retrieveOfferByPartNumberService, times(1))
            .execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST);
        verify(httpOfferPartNumberMapper, times(1)).mapToHttpResponse(ANY_ALL_OFFERS);
    }

    @Test
    void should_return_not_found_when_the_path_variable_is_wrong() throws Exception {
        mockMvc.perform(newHttpRequestNotFound())
            .andExpect(status().isNotFound());

        verify(retrieveOfferByPartNumberService, times(0)).execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST);
        verify(httpOfferPartNumberMapper, times(0)).mapToHttpResponse(any(OfferAggregate.class));
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_paremeters_are_not_used(String testName, String context) throws Exception {
        mock_service_get_offer_list_by_brand_and_partition();
        mock_map_to_httpOfferPartNumber_list();
        //When / then
        mockMvc.perform(newHttpRequestWithParams(context))
            .andExpect(status().isOk());

        verify(retrieveOfferByPartNumberService, times(1)).execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST);
        verify(httpOfferPartNumberMapper, times(1)).mapToHttpResponse(ANY_ALL_OFFERS);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
        );
    }

    private RequestBuilder newHttpRequestWithParams(String content) {
        return MockMvcRequestBuilders.get("/brand/1/partnumber/0001002/offer?hello=343")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.get("/brand/1/partnumber/0001002/offer")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestNotFound() {
        return MockMvcRequestBuilders.get("/brand/1/partnumber//offer/")
            .contentType("application/json");
    }

    private void mock_service_get_offer_list_by_brand_and_partition() {
        Mockito.when(retrieveOfferByPartNumberService.execute(ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST))
            .thenReturn(ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE);
    }

    private void mock_map_to_httpOfferPartNumber_list() {
        Mockito.when(httpOfferPartNumberMapper.mapToHttpResponse(ANY_ALL_OFFERS))
            .thenReturn(ANY_HTTP_OFFER_BY_PART_NUMBER_LIST);
    }

    private final String expectedJson = "[{\"startDate\":\"2020-06-14T00:00:00Z\",\"endDate\":\"2020-12-31T23:59:59Z\",\"price\":35.5,\"currencyIso\":\"EUR\"}]";

}
