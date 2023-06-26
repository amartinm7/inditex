package com.inditex.hiring.infrastructure.framework.offer.repository;

import com.inditex.hiring.domain.offer.BrandId;
import com.inditex.hiring.domain.offer.CreatedAt;
import com.inditex.hiring.domain.offer.CurrencyIso;
import com.inditex.hiring.domain.offer.EndDate;
import com.inditex.hiring.domain.offer.ModifiedAt;
import com.inditex.hiring.domain.offer.Offer;
import com.inditex.hiring.domain.offer.OfferAggregate;
import com.inditex.hiring.domain.offer.OfferEmtpy;
import com.inditex.hiring.domain.offer.OfferId;
import com.inditex.hiring.domain.offer.Price;
import com.inditex.hiring.domain.offer.PriceListId;
import com.inditex.hiring.domain.offer.Priority;
import com.inditex.hiring.domain.offer.PartNumber;
import com.inditex.hiring.domain.offer.StartDate;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class JpaOfferMapper {

    public Offer toOffer(Optional<JpaOffer> jpaOfferOptional) {

        if (jpaOfferOptional.isEmpty()) {
            return OfferEmtpy.of();
        }
        return toOffer(jpaOfferOptional.get());
    }

    private OfferAggregate toOffer(JpaOffer jpaOffer) {
        return OfferAggregate.of(
                new OfferId(jpaOffer.getOfferId()),
                new BrandId(jpaOffer.getBrandId()),
                new StartDate(jpaOffer.getStartDate()),
                new EndDate(jpaOffer.getEndDate()),
                new PriceListId(jpaOffer.getPriceListId()),
                new PartNumber(jpaOffer.getPartNumber()),
                new Priority(jpaOffer.getPriority()),
                new Price(jpaOffer.getPrice()),
                new CurrencyIso(jpaOffer.getCurrencyIso()),
                new CreatedAt(jpaOffer.getCreatedAt()),
                new ModifiedAt(jpaOffer.getModifiedAt())
        );
    }

    public List<OfferAggregate> toOffer(List<JpaOffer> jpaOfferList) {
        return jpaOfferList
                .stream()
                .map(jpaOffer -> toOffer(jpaOffer))
                .collect(toList());
    }
}
