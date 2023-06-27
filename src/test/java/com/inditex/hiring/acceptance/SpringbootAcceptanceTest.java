package com.inditex.hiring.acceptance;

import com.inditex.hiring.infrastructure.framework.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest(
        classes = {EventDatabaseSupport.class, RestTemplateConfig.class, Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class SpringbootAcceptanceTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    protected HttpEntity defaultHttpEntity;

    @BeforeEach
    public void setUp() {
        truncateTable();
        insertRows();
    }

    private void truncateTable() {
        jdbcTemplate.update(TRUNCATE_TABLE, Map.of());
    }

    private void insertRows() {
        jdbcTemplate.update(INSERT_1, Map.of());
        jdbcTemplate.update(INSERT_2, Map.of());
        jdbcTemplate.update(INSERT_3, Map.of());
        jdbcTemplate.update(INSERT_4, Map.of());
    }

    private static final String TRUNCATE_TABLE = "TRUNCATE TABLE PUBLIC.OFFER";
    private static final String INSERT_1 = "INSERT INTO PUBLIC.OFFER (OFFER_ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PARTNUMBER, PRIORITY, PRICE, CURR, CREATED_AT, MODIFIED_AT) VALUES (1, '1', '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, '0001002', 0, 35.50, 'EUR', '2023-06-25 20:27:38', '2023-06-25 20:27:41')";
    private static final String INSERT_2 = "INSERT INTO PUBLIC.OFFER (OFFER_ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PARTNUMBER, PRIORITY, PRICE, CURR, CREATED_AT, MODIFIED_AT) VALUES (2, '1', '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, '0001002', 1, 25.45, 'EUR', '2023-06-25 20:27:38', '2023-06-25 20:27:41')";
    private static final String INSERT_3 = "INSERT INTO PUBLIC.OFFER (OFFER_ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PARTNUMBER, PRIORITY, PRICE, CURR, CREATED_AT, MODIFIED_AT) VALUES (3, '1', '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, '0001002', 1, 30.50, 'EUR', '2023-06-25 20:27:38', '2023-06-25 20:27:41')";
    private static final String INSERT_4 = "INSERT INTO PUBLIC.OFFER (OFFER_ID, BRAND_ID, START_DATE, END_DATE, PRICE_LIST, PARTNUMBER, PRIORITY, PRICE, CURR, CREATED_AT, MODIFIED_AT) VALUES (4, '1', '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, '0001002', 1, 38.95, 'EUR', '2023-06-25 20:27:38', '2023-06-25 20:27:41')";
}
