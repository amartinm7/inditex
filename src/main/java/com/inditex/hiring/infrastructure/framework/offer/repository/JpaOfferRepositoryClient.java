package com.inditex.hiring.infrastructure.framework.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaOfferRepositoryClient extends JpaRepository<JpaOffer, Long> {

    @Query("SELECT Offer FROM JpaOffer Offer WHERE Offer.brandId = :brandId and Offer.partNumber = :partNumber order by Offer.startDate asc, Offer.endDate, Offer.price asc")
    List<JpaOffer> findByBrandIdPartNumber(Integer brandId, String partNumber);
}
