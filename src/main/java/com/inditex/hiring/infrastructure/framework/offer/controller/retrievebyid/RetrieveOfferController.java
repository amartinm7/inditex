package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceRequest;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceResponse;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.OfferEmtpy;
import com.inditex.hiring.domain.offer.exception.OfferNotFound;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveOfferController {

    private final RetrieveOfferService retrieveOfferservice;

    private final OffsetDateTimeHandler offsetDateTimeHandler;

    public RetrieveOfferController(
            RetrieveOfferService retrieveOfferservice,
            OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        this.retrieveOfferservice = retrieveOfferservice;
        this.offsetDateTimeHandler = offsetDateTimeHandler;
    }

    @RequestMapping(value = "/offer/{offerId}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HttpOffer getOfferById(@PathVariable Long offerId) {
        RetrieveOfferServiceResponse response = retrieveOfferservice.execute(new RetrieveOfferServiceRequest(offerId));
        if (response.offer() instanceof OfferEmtpy offerEmtpy) {
            throw new OfferNotFound(offerId);
        }
        return mapToHttpResponse((OfferAggregate) response.offer());
    }

    private HttpOffer mapToHttpResponse(OfferAggregate offerAggregate) {
        return new HttpOffer(
                offerAggregate.offerId().value(),
                offerAggregate.brandId().value(),
                offsetDateTimeHandler.toStringUTC(offerAggregate.startDate().value()),
                offsetDateTimeHandler.toStringUTC(offerAggregate.endDate().value()),
                offerAggregate.priceListId().value(),
                offerAggregate.productPartnumber().value(),
                offerAggregate.priority().value(),
                offerAggregate.price().value(),
                offerAggregate.currencyIso().value()
        );
    }

}
