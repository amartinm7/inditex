package com.inditex.hiring.contract.retrieveall;

import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.contract.SpringbootContractTest;
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

import static com.inditex.hiring.OfferFixtures.ANY_ALL_HTTP_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_DELETE_OFFER_BY_ID_REQUEST;
import static com.inditex.hiring.OfferFixtures.ANY_RETRIEVE_ALL_OFFERS_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RetrieveAllOffersContractTest extends SpringbootContractTest {

    @Autowired
    private RetrieveAllOffersService retrieveAllOffersService;

    @Autowired
    private HttpOfferMapper httpOfferMapper;

    @Test
    void should_retrieve_all_offers() throws Exception {
        //Given
        mock_service_to_retrieve_all_offers();
        mock_map_to_httpOffer_list();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(expectedJson, true));

        verify(retrieveAllOffersService, times(1)).execute();
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_paremeters_are_not_used(String testName, String context) throws Exception {
        //Given
        mock_service_to_retrieve_all_offers();
        mock_map_to_httpOffer_list();
        //When / then
        mockMvc.perform(newHttpRequestWithParams(context))
            .andExpect(status().isOk());

        verify(retrieveAllOffersService, times(1)).execute();
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
        );
    }

    private RequestBuilder newHttpRequestWithParams(String content) {
        return MockMvcRequestBuilders.get("/offer?hello=1234")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.get("/offer")
            .contentType("application/json");
    }

    private void mock_service_to_retrieve_all_offers() {
        Mockito.when(retrieveAllOffersService.execute()).thenReturn(ANY_RETRIEVE_ALL_OFFERS_RESPONSE);
    }

    private void mock_map_to_httpOffer_list() {
        Mockito.when(httpOfferMapper.mapToHttpResponse(ANY_ALL_OFFERS))
            .thenReturn(ANY_ALL_HTTP_OFFERS);
    }

    private final String expectedJson = "[{\"offerId\":1,\"brandId\":1,\"startDate\":\"2020-06-14T00:00:00Z\",\"endDate\":\"2020-12-31T23:59:59Z\",\"priceListId\":1,\"productPartnumber\":\"0001002\",\"priority\":0,\"price\":35.5,\"currencyIso\":\"EUR\"}]";
}
