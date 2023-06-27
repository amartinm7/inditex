package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.OfferAggregate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

public class JdbcOfferRepositoryClient {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final JpaOfferMapper jpaOfferMapper;

    private final FindOffersByIntervalsRowMapper findOffersByIntervalsRowMapper;

    public JdbcOfferRepositoryClient(
            NamedParameterJdbcTemplate jdbcTemplate,
            JpaOfferMapper jpaOfferMapper,
            FindOffersByIntervalsRowMapper findOffersByIntervalsRowMapper
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.jpaOfferMapper = jpaOfferMapper;
        this.findOffersByIntervalsRowMapper = findOffersByIntervalsRowMapper;
    }

    public List<OfferAggregate> findByBrandIdPartNumber(Integer brandId, String partNumber) {
        return jpaOfferMapper.toOfferAggregate(
                jdbcTemplate.query(
                        FIND_OFFERS_BY_INTERVALS,
                        Map.of("brandId", brandId, "partNumber", partNumber),
                        findOffersByIntervalsRowMapper
                )
        );
    }

    public String FIND_OFFERS_BY_INTERVALS = "SELECT * FROM GET_OFFERS_INTERVALS OF WHERE OF.BRAND_ID = :brandId AND OF.PARTNUMBER = :partNumber";
}
