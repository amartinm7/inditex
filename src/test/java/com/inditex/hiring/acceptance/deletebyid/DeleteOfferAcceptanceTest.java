package com.inditex.hiring.acceptance.deletebyid;

import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static com.inditex.hiring.OfferFixtures.NON_EXISTING_OFFER_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOfferAcceptanceTest extends SpringbootAcceptanceTest {

    @Test
    void should_delete_an_offer() {
        ResponseEntity response = restTemplate.exchange(
                "http://localhost:%d/offer/%s".formatted(port, ANY_OFFER_ID),
                HttpMethod.DELETE,
                defaultHttpEntity,
                Object.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(null);
    }

    @Test
    void should_delete_an_no_exists_offer() {
        ResponseEntity response = restTemplate.exchange(
                "http://localhost:%d/offer/%s".formatted(port, NON_EXISTING_OFFER_ID),
                HttpMethod.DELETE,
                defaultHttpEntity,
                Object.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(null);
    }
}
