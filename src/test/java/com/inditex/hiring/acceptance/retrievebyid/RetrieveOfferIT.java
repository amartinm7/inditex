package com.inditex.hiring.acceptance.retrievebyid;

import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.inditex.hiring.OfferFixtures.ANY_OFFER_HTTP;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveOfferIT extends SpringbootAcceptanceTest {

    @Test
    void should_retrieve_an_offer() {
        ResponseEntity<HttpOffer> response = restTemplate.exchange(
                "http://localhost:%d//offer/%s".formatted(port, ANY_OFFER_ID),
                HttpMethod.GET,
                defaultHttpEntity,
                HttpOffer.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(ANY_OFFER_HTTP);
    }
}
