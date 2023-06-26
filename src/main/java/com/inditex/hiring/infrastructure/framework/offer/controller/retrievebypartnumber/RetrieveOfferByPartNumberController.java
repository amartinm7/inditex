package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebypartnumber;

import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberService;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartitionServiceRequest;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartitionServiceResponse;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferByPartNumberMapper;
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
public class RetrieveOfferByPartNumberController {

    private final HttpOfferByPartNumberMapper httpOfferByPartNumberMapper;

    private final RetrieveOfferByPartNumberService retrieveOfferByPartNumberService;

    public RetrieveOfferByPartNumberController(
            RetrieveOfferByPartNumberService retrieveOfferByPartNumberService,
            HttpOfferByPartNumberMapper httpOfferByPartNumberMapper
    ) {
        this.retrieveOfferByPartNumberService = retrieveOfferByPartNumberService;
        this.httpOfferByPartNumberMapper = httpOfferByPartNumberMapper;
    }

    @RequestMapping(value = "brand/{brandId}/partnumber/{partNumber}/offer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<HttpOfferByPartNumber> getOfferByPartNumber(
            @PathVariable Integer brandId,
            @PathVariable String partNumber
    ) {
        RetrieveOfferByPartitionServiceResponse response =
                retrieveOfferByPartNumberService.execute(
                        new RetrieveOfferByPartitionServiceRequest(brandId, partNumber)
                );
        return httpOfferByPartNumberMapper.mapToHttpResponse(response.offerList());
    }
}
