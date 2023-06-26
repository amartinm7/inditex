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
import com.inditex.hiring.domain.offer.OfferRepository;
import com.inditex.hiring.domain.offer.Price;
import com.inditex.hiring.domain.offer.PriceListId;
import com.inditex.hiring.domain.offer.Priority;
import com.inditex.hiring.domain.offer.ProductPartNumber;
import com.inditex.hiring.domain.offer.StartDate;

import java.util.Optional;

public class OfferRepositoryProvider implements OfferRepository {

    private JpaOfferRepositoryClient jpaOfferRepositoryClient;

    public OfferRepositoryProvider(JpaOfferRepositoryClient jpaOfferRepositoryClient) {
        this.jpaOfferRepositoryClient = jpaOfferRepositoryClient;
    }

    @Override
    public Offer findById(Long offerId) {
        return toOffer(jpaOfferRepositoryClient.findById(offerId));
    }

    private Offer toOffer(Optional<JpaOffer> jpaOfferOptional) {

        if (jpaOfferOptional.isEmpty()) {
            return OfferEmtpy.of();
        }
        JpaOffer jpaOffer = jpaOfferOptional.get();
        return OfferAggregate.of(
                new OfferId(jpaOffer.getOfferId()),
                new BrandId(jpaOffer.getBrandId()),
                new StartDate(jpaOffer.getStartDate()),
                new EndDate(jpaOffer.getEndDate()),
                new PriceListId(jpaOffer.getPriceListId()),
                new ProductPartNumber(jpaOffer.getProductPartnumber()),
                new Priority(jpaOffer.getPriority()),
                new Price(jpaOffer.getPrice()),
                new CurrencyIso(jpaOffer.getCurrencyIso()),
                new CreatedAt(jpaOffer.getCreatedAt()),
                new ModifiedAt(jpaOffer.getModifiedAt())
        );
    }
}
