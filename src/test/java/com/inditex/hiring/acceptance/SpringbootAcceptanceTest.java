package com.inditex.hiring.acceptance;

import com.inditex.hiring.infrastructure.framework.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

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
}
