package com.inditex.hiring.acceptance.create;

import com.inditex.hiring.OfferFixtures;
import com.inditex.hiring.acceptance.SpringbootAcceptanceTest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import org.assertj.core.api.ObjectEnumerableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static com.inditex.hiring.OfferFixtures.ANY_HTTP_OFFER;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_ID;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateOfferAcceptanceTest extends SpringbootAcceptanceTest {
    @Test
    void should_create_an_offer() {
        HttpEntity<HttpOffer> httpRequest = getHttpEntity(ANY_HTTP_OFFER);
        ResponseEntity response = restTemplate.exchange(
                "http://localhost:%d/offer".formatted(port),
                HttpMethod.POST,
                httpRequest,
                Object.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(null);
    }

    protected <T> HttpEntity<T> getHttpEntity (T request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<T>(request, requestHeaders);
    }
}
