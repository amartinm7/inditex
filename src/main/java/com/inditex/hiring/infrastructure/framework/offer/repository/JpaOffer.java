package com.inditex.hiring.infrastructure.framework.offer.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
@Table(name = "offer")
public class JpaOffer {
    @Id
    @Column(name = "offer_id")
    Long offerId;
    @Column(name = "brand_id")
    Integer brandId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date")
    OffsetDateTime startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date")
    OffsetDateTime endDate;
    @Column(name = "price_list")
    Long priceListId;
    @Column(name = "partnumber")
    String partNumber;
    @Column(name = "priority")
    Integer priority;
    @Column(name = "price")
    BigDecimal price;
    @Column(name = "curr")
    String currencyIso;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    OffsetDateTime createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    OffsetDateTime modifiedAt;


    public JpaOffer() {
    }

    public JpaOffer(Long offerId, Integer brandId, OffsetDateTime startDate, OffsetDateTime endDate, Long priceListId, String partNumber, Integer priority, BigDecimal price, String currencyIso, OffsetDateTime createdAt, OffsetDateTime modifiedAt) {
        this.offerId = offerId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priceListId = priceListId;
        this.partNumber = partNumber;
        this.priority = priority;
        this.price = price;
        this.currencyIso = currencyIso;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public static JpaOffer of(Long offerId, Integer brandId, OffsetDateTime startDate, OffsetDateTime endDate, Long priceListId, String productPartnumber,
                              Integer priority, BigDecimal price, String currencyIso, OffsetDateTime createdAt, OffsetDateTime modifiedAt) {
        return new JpaOffer(offerId, brandId, startDate, endDate, priceListId, productPartnumber, priority, price, currencyIso, createdAt, modifiedAt);
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    public Long getPriceListId() {
        return priceListId;
    }

    public void setPriceListId(Long priceListId) {
        this.priceListId = priceListId;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String productPartnumber) {
        this.partNumber = productPartnumber;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyIso() {
        return currencyIso;
    }

    public void setCurrencyIso(String currencyIso) {
        this.currencyIso = currencyIso;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(OffsetDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JpaOffer jpaOffer)) return false;
        return Objects.equals(getOfferId(), jpaOffer.getOfferId()) && Objects.equals(getBrandId(), jpaOffer.getBrandId()) && Objects.equals(getStartDate(), jpaOffer.getStartDate()) && Objects.equals(getEndDate(), jpaOffer.getEndDate()) && Objects.equals(getPriceListId(), jpaOffer.getPriceListId()) && Objects.equals(getPartNumber(), jpaOffer.getPartNumber()) && Objects.equals(getPriority(), jpaOffer.getPriority()) && Objects.equals(getPrice(), jpaOffer.getPrice()) && Objects.equals(getCurrencyIso(), jpaOffer.getCurrencyIso()) && Objects.equals(getCreatedAt(), jpaOffer.getCreatedAt()) && Objects.equals(getModifiedAt(), jpaOffer.getModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOfferId(), getBrandId(), getStartDate(), getEndDate(), getPriceListId(), getPartNumber(), getPriority(), getPrice(), getCurrencyIso(), getCreatedAt(), getModifiedAt());
    }

    @Override
    public String toString() {
        return "JpaOffer{" +
                "offerId=" + offerId +
                ", brandId=" + brandId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", priceListId=" + priceListId +
                ", productPartnumber='" + partNumber + '\'' +
                ", priority=" + priority +
                ", price=" + price +
                ", currencyIso='" + currencyIso + '\'' +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}