package com.inditex.hiring.contract.deletebyid;

import com.inditex.hiring.application.offer.deletebyid.DeleteOfferService;
import com.inditex.hiring.application.offer.deletebyid.DeleteOfferServiceRequest;
import com.inditex.hiring.contract.SpringbootContractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static com.inditex.hiring.OfferFixtures.ANY_DELETE_OFFER_BY_ID_REQUEST;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteOfferContractTest extends SpringbootContractTest {

    @Autowired
    private DeleteOfferService deleteOfferService;

    @Test
    void should_delete_an_offer() throws Exception {
        //Given
        mock_service_to_delete_an_offer();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(deleteOfferService, times(1)).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }

    @Test
    void should_return_not_found_when_there_is_not_path_variable() throws Exception {
        mockMvc.perform(newHttpRequestNotFound())
            .andExpect(status().isNotFound());

        verify(deleteOfferService, times(0)).execute(any(DeleteOfferServiceRequest.class));
    }

    @Test
    void should_return_bad_request_when_path_variable_is_wrong() throws Exception {
        mockMvc.perform(newHttpRequestBadRequestBadId())
            .andExpect(status().isBadRequest());

        verify(deleteOfferService, times(0)).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_paremeters_are_not_used(String testName, String context) throws Exception {
        mockMvc.perform(newHttpRequestWithParams(context))
            .andExpect(status().isOk());

        verify(deleteOfferService, times(1)).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
        );
    }

    private RequestBuilder newHttpRequestWithParams(String content) {
        return MockMvcRequestBuilders.delete("/offer/1?hello=343")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.delete("/offer/1")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestNotFound() {
        return MockMvcRequestBuilders.delete("/offer/")
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestBadRequestBadId() {
        return MockMvcRequestBuilders.delete("/offer/dsfsdf")
            .contentType("application/json");
    }

    private void mock_service_to_delete_an_offer() {
        doNothing().when(deleteOfferService).execute(ANY_DELETE_OFFER_BY_ID_REQUEST);
    }
}
