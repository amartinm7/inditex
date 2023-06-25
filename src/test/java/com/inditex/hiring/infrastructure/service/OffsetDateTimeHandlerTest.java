package com.inditex.hiring.infrastructure.service;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import static com.inditex.hiring.OfferFixtures.ANY_START_DATE_STR;
import static org.assertj.core.api.Assertions.assertThat;



class OffsetDateTimeHandlerTest {

    @Test
    public void should_return_a_string_format_in_UTC_of_an_offsetDateTime() {
        OffsetDateTime ANY_START_DATE = OffsetDateTime.parse(ANY_START_DATE_STR).truncatedTo(ChronoUnit.SECONDS);
        assertThat(ANY_START_DATE_STR).isEqualTo(new OffsetDateTimeHandler().toStringUTC(ANY_START_DATE));
    }
}