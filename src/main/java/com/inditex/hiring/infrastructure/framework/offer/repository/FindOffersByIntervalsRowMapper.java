package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FindOffersByIntervalsRowMapper implements RowMapper<JpaOffer> {

    private final OffsetDateTimeHandler offsetDateTimeHandler;

    public FindOffersByIntervalsRowMapper(OffsetDateTimeHandler offsetDateTimeHandler) {
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }
    @Override
    public JpaOffer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JpaOffer.of(
                rs.getLong("offer_id"),
                rs.getInt("brand_id"),
                offsetDateTimeHandler.toOffsetDateTimeWithoutFormat(rs.getString("start_date")),
                offsetDateTimeHandler.toOffsetDateTimeWithoutFormat(rs.getString("end_date")),
                rs.getLong("price_list"),
                rs.getString("partnumber"),
                rs.getInt("priority"),
                new BigDecimal(rs.getString("price")),
                rs.getString("curr"),
                offsetDateTimeHandler.toOffsetDateTimeWithoutFormat(rs.getString("created_at")),
                offsetDateTimeHandler.toOffsetDateTimeWithoutFormat(rs.getString("modified_at"))
        );
    }
}
