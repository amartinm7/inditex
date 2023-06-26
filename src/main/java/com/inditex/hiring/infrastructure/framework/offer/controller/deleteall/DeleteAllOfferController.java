package com.inditex.hiring.infrastructure.framework.offer.controller.deleteall;

import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAllOfferController {

    private final DeleteAllOffersService deleteAllOffersService;

    public DeleteAllOfferController(
            DeleteAllOffersService deleteAllOffersService
    ) {
        this.deleteAllOffersService = deleteAllOffersService;
    }

    @RequestMapping(value = "/offer", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAllOffers() {
        deleteAllOffersService.execute();
    }
}
