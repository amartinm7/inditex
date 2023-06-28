package com.inditex.hiring.contract.retrievebyid;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.contract.SpringbootContractTest;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
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

import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_AGGREGATE;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_OFFER_RESPONSE_EMPTY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveOfferContractTest extends SpringbootContractTest {

    @Autowired
    private RetrieveOfferService retrieveOfferservice;

    @Autowired
    private HttpOfferMapper httpOfferMapper;

    @Test
    void should_retrieve_an_offer() throws Exception {
        //Given
        mock_service_and_retrieve_a_valid_offer();
        mock_map_to_httpOffer();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(expectedJson, true));

        verify(retrieveOfferservice, times(1)).execute(ANY_RETRIEVE_OFFER_REQUEST);
        verify(httpOfferMapper, times(1)).mapToHttpResponse(ANY_OFFER_AGGREGATE);
    }

    @Test
    void should_retrieve_a_not_found_if_offer_doesnt_exist() throws Exception {
        //Given
        mock_service_and_retrieve_an_empty_offer();
        mock_map_to_httpOffer();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isNotFound());

        verify(retrieveOfferservice, times(1)).execute(ANY_RETRIEVE_OFFER_REQUEST);
        verify(httpOfferMapper, times(0)).mapToHttpResponse(any(OfferAggregate.class));
    }

    @Test
    void should_return_not_found_when_there_is_not_path_variable() throws Exception {
        //Given /When / then
        mockMvc.perform(newHttpRequestNotFound())
            .andExpect(status().isNotFound());

        verify(retrieveOfferservice, times(0)).execute(ANY_RETRIEVE_OFFER_REQUEST);
        verify(httpOfferMapper, times(0)).mapToHttpResponse(any(OfferAggregate.class));
    }

    @Test
    void should_return_bad_request_when_path_variable_is_wrong() throws Exception {
        mockMvc.perform(newHttpRequestBadRequestBadId())
            .andExpect(status().isBadRequest());

        verify(retrieveOfferservice, times(0)).execute(ANY_RETRIEVE_OFFER_REQUEST);
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_paremeters_are_not_used(String testName, String context) throws Exception {
        mock_service_and_retrieve_a_valid_offer();
        mock_map_to_httpOffer();
        //When / then
        mockMvc.perform(newHttpRequestWithParams(context))
            .andExpect(status().isOk());

        verify(retrieveOfferservice, times(1)).execute(ANY_RETRIEVE_OFFER_REQUEST);
        verify(httpOfferMapper, times(1)).mapToHttpResponse(any(OfferAggregate.class));
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
        );
    }

    private RequestBuilder newHttpRequestWithParams(String content) {
        return MockMvcRequestBuilders.get("/offer/1?hello=343")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.get("/offer/1")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestNotFound() {
        return MockMvcRequestBuilders.get("/offer/")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestBadRequestBadId() {
        return MockMvcRequestBuilders.get("/offer/dsfsdf")
            .contentType("application/json");
    }

    private void mock_service_and_retrieve_a_valid_offer() {
        Mockito.when(retrieveOfferservice.execute(ANY_RETRIEVE_OFFER_REQUEST))
            .thenReturn(ANY_RETRIEVE_OFFER_RESPONSE);
    }

    private void mock_map_to_httpOffer() {
        Mockito.when(httpOfferMapper.mapToHttpResponse(ANY_OFFER_AGGREGATE))
            .thenReturn(ANY_HTTP_OFFER);
    }

    private void mock_service_and_retrieve_an_empty_offer() {
        Mockito.when(retrieveOfferservice.execute(ANY_RETRIEVE_OFFER_REQUEST))
            .thenReturn(ANY_RETRIEVE_OFFER_RESPONSE_EMPTY);
    }

    private final String expectedJson = "{\"offerId\":1,\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00Z\",\"endDate\":\"2020-12-31T23:59:59Z\",\"priceListId\":1,\"productPartnumber\":\"0001002\",\"priority\":0,\"price\":35.5,\"currencyIso\":\"EUR\"}";

}
