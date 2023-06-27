package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static com.inditex.hiring.OfferFixtures.ANY_ALL_JPA_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_ALL_OFFERS;
import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_PART_NUMBER;
import static com.inditex.hiring.OfferFixtures.ANY_START_DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class JdbcOfferRepositoryClientTest {

    private final NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);

    private final JpaOfferMapper jpaOfferMapper = mock(JpaOfferMapper.class);

    private final OffsetDateTimeHandler offsetDateTimeHandler = mock(OffsetDateTimeHandler.class);

    private final FindOffersByIntervalsRowMapper findOffersByIntervalsRowMapper =
            new FindOffersByIntervalsRowMapper(offsetDateTimeHandler);

    private final JdbcOfferRepositoryClient jdbcOfferRepositoryClient = new JdbcOfferRepositoryClient(
            jdbcTemplate, jpaOfferMapper, findOffersByIntervalsRowMapper
    );

    @Test
    public void should_return_a_list_of_offers() throws SQLException {
        //Given
        mock_mapper_jpa_offer_to_offer();
        mock_jdbcTemplate_query();
        mock_offsetDatetime_to_date();
        //When
        List<OfferAggregate> response = jdbcOfferRepositoryClient.findByBrandIdPartNumber(ANY_BRAND_ID, ANY_PART_NUMBER);
        //Then
        assertThat(response).isEqualTo(ANY_ALL_OFFERS);
        verify(jdbcTemplate, times(1)).query(
                jdbcOfferRepositoryClient.FIND_OFFERS_BY_INTERVALS,
                Map.of("brandId", ANY_BRAND_ID, "partNumber", ANY_PART_NUMBER),
                findOffersByIntervalsRowMapper);
        verify(jpaOfferMapper, times(1)).toOfferAggregate(ANY_ALL_JPA_OFFERS);
    }

    private void mock_mapper_jpa_offer_to_offer() {
        when(jpaOfferMapper.toOfferAggregate(ANY_ALL_JPA_OFFERS)).thenReturn(ANY_ALL_OFFERS);
    }

    private void mock_jdbcTemplate_query() {
        when(jdbcTemplate.query(
                        jdbcOfferRepositoryClient.FIND_OFFERS_BY_INTERVALS,
                        Map.of("brandId", ANY_BRAND_ID, "partNumber", ANY_PART_NUMBER),
                        findOffersByIntervalsRowMapper
                )
        ).thenReturn(ANY_ALL_JPA_OFFERS);
    }

    private void mock_offsetDatetime_to_date() {
        when(offsetDateTimeHandler.toOffsetDateTimeWithoutFormat(any())
        ).thenReturn(ANY_START_DATE);
    }
}