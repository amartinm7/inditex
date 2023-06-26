package com.inditex.hiring.infrastructure.framework.offer.controller.deletebyid;

import com.inditex.hiring.application.offer.deletebyid.DeleteOfferService;
import com.inditex.hiring.application.offer.deletebyid.DeleteOfferServiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteOfferController {

    private final DeleteOfferService deleteOfferService;

    public DeleteOfferController(
            DeleteOfferService deleteOfferService
            ) {
        this.deleteOfferService = deleteOfferService;
    }

    @RequestMapping(value = "/offer/{offerId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteOfferById(@PathVariable Long offerId) {
        deleteOfferService.execute(new DeleteOfferServiceRequest(offerId));
    }
}
