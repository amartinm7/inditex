package com.inditex.hiring.infrastructure.framework.offer.controller.create;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.application.offer.create.CreateOfferServiceRequest;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateOfferController {
    private final CreateOfferService createOfferService;
    private final HttpOfferMapper httpOfferMapper;

    public CreateOfferController(
            CreateOfferService createOfferService,
            HttpOfferMapper httpOfferMapper
    ) {
        this.createOfferService = createOfferService;
        this.httpOfferMapper = httpOfferMapper;
    }

    @RequestMapping(value = "/offer", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOffer(@RequestBody @Valid HttpOffer httpOffer) {
        createOfferService.execute(
                new CreateOfferServiceRequest(httpOfferMapper.mapToOffer(httpOffer))
        );
    }
}
