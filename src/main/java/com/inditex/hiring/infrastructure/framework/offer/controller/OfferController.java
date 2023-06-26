package com.inditex.hiring.infrastructure.framework.offer.controller;

import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * You can change this controller but please do not change ends points signatures & payloads.
 */
@RestController
public class OfferController {

  @RequestMapping(value = "/offer", method = RequestMethod.POST, consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void createNewOffer(@RequestBody @Valid HttpOffer httpOffer) {

    //TODO implement it!.

  }

  @RequestMapping(value = "/offer", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteAllOffers() {

    //TODO implement it!.

  }

  @RequestMapping(value = "/offer/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.OK)
  public void deleteOfferById(@RequestParam Long id) {

    //TODO implement it!.

  }


}
