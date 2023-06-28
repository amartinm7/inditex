package com.inditex.hiring.contract;

import com.inditex.hiring.infrastructure.framework.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
    classes = {ContractTestConfiguration.class, Application.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public abstract class SpringbootContractTest {

    @LocalServerPort
    protected int port;

    @Autowired
    protected MockMvc mockMvc;
}
