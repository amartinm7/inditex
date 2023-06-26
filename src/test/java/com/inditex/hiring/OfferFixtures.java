package com.inditex.hiring;

import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersServiceResponse;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceRequest;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferServiceResponse;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberServiceRequest;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberServiceResponse;
import com.inditex.hiring.domain.offer.BrandId;
import com.inditex.hiring.domain.offer.CreatedAt;
import com.inditex.hiring.domain.offer.CurrencyIso;
import com.inditex.hiring.domain.offer.EndDate;
import com.inditex.hiring.domain.offer.ModifiedAt;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.OfferEmtpy;
import com.inditex.hiring.domain.offer.OfferId;
import com.inditex.hiring.domain.offer.Price;
import com.inditex.hiring.domain.offer.PriceListId;
import com.inditex.hiring.domain.offer.Priority;
import com.inditex.hiring.domain.offer.ProductPartNumber;
import com.inditex.hiring.domain.offer.StartDate;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOffer;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import com.inditex.hiring.infrastructure.framework.offer.repository.JpaOffer;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class OfferFixtures {

    public static Long ANY_OFFER_ID = 1L;

    public static Integer ANY_BRAND_ID = 1;

    public static String ANY_START_DATE_STR = "2020-06-14T00:00:00Z";

    public static OffsetDateTime ANY_START_DATE = new OffsetDateTimeHandler().toOffsetDateTime(ANY_START_DATE_STR);

    public static String ANY_END_DATE_STR = "2020-12-31T23:59:59Z";

    public static OffsetDateTime ANY_END_DATE = new OffsetDateTimeHandler().toOffsetDateTime(ANY_END_DATE_STR);

    public static Long ANY_PRICELIST_ID = 1L;

    public static String ANY_PRODUCT_PART_NUMBER = "0001002";

    public static Integer ANY_PRIORITY = 0;

    public static BigDecimal ANY_PRICE = new BigDecimal(35.50);

    public static String ANY_CURRENCY_ISO = "EUR";

    public static final HttpOffer ANY_HTTP_OFFER = HttpOffer.of(
            ANY_OFFER_ID,
            ANY_BRAND_ID,
            ANY_START_DATE_STR,
            ANY_END_DATE_STR,
            ANY_PRICELIST_ID,
            ANY_PRODUCT_PART_NUMBER,
            ANY_PRIORITY,
            ANY_PRICE,
            ANY_CURRENCY_ISO
    );

    public static final List<HttpOffer> ANY_ALL_HTTP_OFFERS = List.of(ANY_HTTP_OFFER);

    public static OfferId OFFER_ID = new OfferId(ANY_OFFER_ID);
    public static BrandId BRAND_ID = new BrandId(ANY_BRAND_ID);
    public static StartDate START_DATE = new StartDate(ANY_START_DATE);
    public static EndDate END_DATE = new EndDate(ANY_END_DATE);
    public static PriceListId PRICELIST = new PriceListId(ANY_PRICELIST_ID);
    public static ProductPartNumber PRODUCT_PART_NUMBER = new ProductPartNumber(ANY_PRODUCT_PART_NUMBER);
    public static Priority PRIORITY = new Priority(ANY_PRIORITY);
    public static Price PRICE = new Price(ANY_PRICE);
    public static CurrencyIso CURRENCY_ISO = new CurrencyIso(ANY_CURRENCY_ISO);

    public static CreatedAt CREATED_AT = new CreatedAt(ANY_START_DATE);

    public static ModifiedAt MODIFIED_AT = new ModifiedAt(ANY_START_DATE);

    public static final OfferAggregate ANY_OFFER_AGGREGATE = OfferAggregate.of(
            OFFER_ID,
            BRAND_ID,
            START_DATE,
            END_DATE,
            PRICELIST,
            PRODUCT_PART_NUMBER,
            PRIORITY,
            PRICE,
            CURRENCY_ISO,
            CREATED_AT,
            MODIFIED_AT
    );

    public static final List<OfferAggregate> ANY_ALL_OFFERS = List.of(ANY_OFFER_AGGREGATE);

    public static final OfferEmtpy ANY_OFFER_EMPTY = OfferEmtpy.of();

    public static final RetrieveOfferServiceRequest ANY_RETRIEVE_OFFER_REQUEST = new RetrieveOfferServiceRequest(ANY_OFFER_ID);

    public static final RetrieveOfferServiceResponse ANY_RETRIEVE_OFFER_RESPONSE = new RetrieveOfferServiceResponse(ANY_OFFER_AGGREGATE);

    public static final RetrieveOfferServiceResponse ANY_RETRIEVE_OFFER_RESPONSE_EMPTY = new RetrieveOfferServiceResponse(OfferEmtpy.of());

    public static final RetrieveAllOffersServiceResponse ANY_RETRIEVE_ALL_OFFERS_RESPONSE = new RetrieveAllOffersServiceResponse(ANY_ALL_OFFERS);

    public static final JpaOffer ANY_JPA_OFFER = JpaOffer.of(
            ANY_OFFER_ID,
            ANY_BRAND_ID,
            ANY_START_DATE,
            ANY_END_DATE,
            ANY_PRICELIST_ID,
            ANY_PRODUCT_PART_NUMBER,
            ANY_PRIORITY,
            ANY_PRICE,
            ANY_CURRENCY_ISO,
            ANY_START_DATE,
            ANY_START_DATE
    );
    public static final List<JpaOffer> ANY_ALL_JPA_OFFERS = List.of(ANY_JPA_OFFER);
    public static final HttpOfferByPartNumber ANY_OFFER_BY_PART_NUMBER =
            HttpOfferByPartNumber.of(ANY_START_DATE_STR, ANY_END_DATE_STR, ANY_PRICE, ANY_CURRENCY_ISO);
    public static final List<HttpOfferByPartNumber> ANY_HTTP_OFFER_BY_PART_NUMBER_LIST = List.of(ANY_OFFER_BY_PART_NUMBER);

    public static final RetrieveOfferByPartNumberServiceRequest ANY_RETRIEVE_OFFER_BY_PART_NUMB_REQUEST =
            new RetrieveOfferByPartNumberServiceRequest(ANY_BRAND_ID, ANY_PRODUCT_PART_NUMBER);

    public static final RetrieveOfferByPartNumberServiceResponse ANY_RETRIEVE_OFFER_BY_PART_NUMB_RESPONSE =
            new RetrieveOfferByPartNumberServiceResponse(ANY_ALL_OFFERS);
}
