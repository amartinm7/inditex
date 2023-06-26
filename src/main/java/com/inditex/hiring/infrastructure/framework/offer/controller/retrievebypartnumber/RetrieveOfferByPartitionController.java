package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebypartnumber;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RetrieveOfferByPartitionController {

    private final HttpOfferMapper httpOfferMapper;

    private final RetrieveOfferService retrieveOfferservice;

    public RetrieveOfferByPartitionController(
            RetrieveOfferService retrieveOfferservice,
            HttpOfferMapper httpOfferMapper
    ) {
        this.retrieveOfferservice = retrieveOfferservice;
        this.httpOfferMapper = httpOfferMapper;
    }

    @RequestMapping(value = "brand/{brandId}/partnumber/{partNumber}/offer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<HttpOfferByPartNumber> getOfferByPartNumber(
            @PathVariable Integer brandId,
            @PathVariable String partNumber
    ) {

        //TODO implement it!.
        return new ArrayList<HttpOfferByPartNumber>();
    }
}
