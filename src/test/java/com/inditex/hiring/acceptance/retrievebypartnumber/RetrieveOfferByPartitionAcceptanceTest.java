package com.inditex.hiring.acceptance.retrievebypartnumber;

import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_PRODUCT_PART_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveOfferByPartitionAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_retrieve_an_offer_by_brand_and_partition_number() {
        ResponseEntity<HttpOffer> response = restTemplate.exchange(
                "http://localhost:%d/brand/%d/partnumber/%s/offer"
                        .formatted(port, ANY_BRAND_ID, ANY_PRODUCT_PART_NUMBER),
                HttpMethod.GET,
                defaultHttpEntity,
                HttpOffer.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ANY_HTTP_OFFER);
    }
}
