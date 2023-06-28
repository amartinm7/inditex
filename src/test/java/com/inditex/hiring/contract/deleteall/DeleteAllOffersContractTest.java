package com.inditex.hiring.contract.deleteall;

import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import com.inditex.hiring.contract.SpringbootContractTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeleteAllOffersContractTest extends SpringbootContractTest {

    @Autowired
    private DeleteAllOffersService deleteAllOffersService;

    @Test
    void should_delete_all_offers() throws Exception {
        //Given
        mock_service_to_delete_all_offers();
        //When / then
        mockMvc.perform(newHttpRequestSuccess())
            .andExpect(status().isOk());

        verify(deleteAllOffersService, times(1)).execute();
    }

    @ParameterizedTest(name = "{index}: {0} {1}")
    @MethodSource("provider")
    void should_validate_request_body_and_paremeters_are_not_used(String testName, String context) throws Exception {
        mockMvc.perform(newHttpRequestWithParams(context))
            .andExpect(status().isOk());

        verify(deleteAllOffersService, times(1)).execute();
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
            Arguments.of("Null brandId", "{\"brandId\": ,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Empty brandId", "{\"brandId\": \"\",\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}"),
            Arguments.of("Max brandId", "{\"brandId\": 9999999999,\"currencyIso\": \"EUR\",\"endDate\": \"2020-12-31T23:59:59Z\",\"offerId\": 1,\"price\": 35.5,\"priceListId\": 1,\"priority\": 0,\"productPartnumber\": \"0001002\",\"startDate\": \"2020-06-14T00:00:00Z\"}")
        );
    }

    private RequestBuilder newHttpRequestWithParams(String content) {
        return MockMvcRequestBuilders.delete("/offer?hello=343")
            .content(content)
            .contentType("application/json");
    }

    private RequestBuilder newHttpRequestSuccess() {
        return MockMvcRequestBuilders.delete("/offer")
            .contentType("application/json");
    }

    private void mock_service_to_delete_all_offers() {
        doNothing().when(deleteAllOffersService).execute();
    }
}
