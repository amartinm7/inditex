package com.inditex.hiring.acceptance.retrievebypartnumber;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_PART_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveOfferByPartNumberAcceptanceTest extends SpringbootAcceptanceTest {

    @Value("classpath:/fixtures/offer/retrieve_an_offer_list_by_brand_and_part_number.json")
    private org.springframework.core.io.Resource offerListResource;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_retrieve_an_offer_list_by_brand_and_part_number() throws IOException {
        //Given
        HttpOfferByPartNumber[] expected = objectMapper.readValue(offerListResource.getInputStream(), HttpOfferByPartNumber[].class);
        //When
        ResponseEntity<HttpOfferByPartNumber[]> response = restTemplate.exchange(
                "http://localhost:%d/brand/%d/partnumber/%s/offer"
                        .formatted(port, ANY_BRAND_ID, ANY_PART_NUMBER),
                HttpMethod.GET,
                defaultHttpEntity,
                HttpOfferByPartNumber[].class
        );
        //Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expected);
    }
}
