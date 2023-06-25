package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.infrastructure.framework.offer.controller.dto.Offer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveOfferController {

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Offer getOfferById(Long offerId) {

        //TODO implement it!.
        return new Offer();
    }
}
