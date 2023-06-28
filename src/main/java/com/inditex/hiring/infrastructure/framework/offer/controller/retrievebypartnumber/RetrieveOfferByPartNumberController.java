package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebypartnumber;

import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberService;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberServiceRequest;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberServiceResponse;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferByPartNumberMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
            @PathVariable
            @Valid
            @NotNull(message = "brandId cannot be blank")
            @Max(value = 999999999)
            Integer brandId,
            @PathVariable
            @Valid
            @NotNull(message = "productPartnumber cannot be blank")
            @NotEmpty(message = "productPartnumber cannot be empty")
            String partNumber
    ) {
        RetrieveOfferByPartNumberServiceResponse response =
                retrieveOfferByPartNumberService.execute(
                        new RetrieveOfferByPartNumberServiceRequest(brandId, partNumber)
                );
        return httpOfferByPartNumberMapper.mapToHttpResponse(response.offerList());
    }
}
