package com.inditex.hiring.infrastructure.framework.offer.controller.retrieveall;

import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersServiceResponse;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RetrieveAllOffersController {

    private final HttpOfferMapper httpOfferMapper;
    private final RetrieveAllOffersService retrieveAllOffersService;

    public RetrieveAllOffersController(
            RetrieveAllOffersService retrieveAllOffersService,
            HttpOfferMapper httpOfferMapper
    ) {
        this.httpOfferMapper = httpOfferMapper;
        this.retrieveAllOffersService = retrieveAllOffersService;
    }

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<HttpOffer> getAllOffers() {
        RetrieveAllOffersServiceResponse response = retrieveAllOffersService.execute();
        //TODO implement it!.
        return httpOfferMapper.mapToHttpResponse(response.offerList());
    }
}
