package com.inditex.hiring.acceptance.retrieveall;

import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveAllOffersIT extends SpringbootAcceptanceTest {

    @Test
    void should_retrieve_all_offers() {
        ResponseEntity<HttpOffer[]> response = restTemplate.exchange(
                "http://localhost:%d/offer".formatted(port),
                HttpMethod.GET,
                defaultHttpEntity,
                HttpOffer[].class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().length).isEqualTo(4);
    }
}
