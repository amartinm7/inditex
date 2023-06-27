package com.inditex.hiring.contract;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContractTestConfiguration {

    @MockBean
    private OffsetDateTimeHandler offsetDateTimeHandler;

    @MockBean
    public CreateOfferService createOfferService;

    @MockBean
    private DeleteAllOffersService deleteAllOffersService;
}
