package com.inditex.hiring.contract;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import com.inditex.hiring.application.offer.deletebyid.DeleteOfferService;
import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContractTestConfiguration {

    @MockBean
    public OffsetDateTimeHandler offsetDateTimeHandler;

    @MockBean
    public HttpOfferMapper httpOfferMapper;

    @MockBean
    public CreateOfferService createOfferService;

    @MockBean
    public DeleteAllOffersService deleteAllOffersService;

    @MockBean
    public DeleteOfferService deleteOfferService;

    @MockBean
    public RetrieveAllOffersService retrieveAllOffersService;

    @MockBean
    public RetrieveOfferService retrieveOfferservice;

}
