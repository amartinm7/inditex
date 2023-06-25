package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebyid;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceRequest;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceResponse;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.Offer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.http.HttpStatus;
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

    @RequestMapping(value = "/offer/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Offer getOfferById(Long offerId) {
        RetrieveOfferServiceResponse response = retrieveOfferservice.execute(new RetrieveOfferServiceRequest(offerId));
        return mapToHttpResponse(response);
    }

    private Offer mapToHttpResponse(RetrieveOfferServiceResponse response) {
        return new Offer(
                response.offer().offerId().value(),
                response.offer().brandId().value(),
                offsetDateTimeHandler.toStringUTC(response.offer().startDate().value()),
                offsetDateTimeHandler.toStringUTC(response.offer().endDate().value()),
                response.offer().priceListId().value(),
                response.offer().productPartnumber().value(),
                response.offer().priority().value(),
                response.offer().price().value(),
                response.offer().currencyIso().value()
        );
    }

}
