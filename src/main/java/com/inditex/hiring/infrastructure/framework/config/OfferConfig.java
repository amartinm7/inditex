package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.domain.offer.OfferRepository;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import com.inditex.hiring.infrastructure.framework.offer.repository.JpaOfferMapper;
import com.inditex.hiring.infrastructure.framework.offer.repository.JpaOfferRepositoryClient;
import com.inditex.hiring.infrastructure.framework.offer.repository.OfferRepositoryProvider;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferConfig {

    @Bean
    public RetrieveOfferService retrieveOfferService(OfferRepository offerRepository) {
        return new RetrieveOfferService(offerRepository);
    }

    @Bean
    public RetrieveAllOffersService retrieveAllOffersService(OfferRepository offerRepository) {
        return new RetrieveAllOffersService(offerRepository);
    }

    @Bean
    public OffsetDateTimeHandler offsetDateTimeHandler() {
        return new OffsetDateTimeHandler();
    }

    @Bean
    public HttpOfferMapper httpOfferMapper(OffsetDateTimeHandler offsetDateTimeHandler) {
        return new HttpOfferMapper(offsetDateTimeHandler);
    }

    @Bean
    public JpaOfferMapper jpaOfferMapper() {
        return new JpaOfferMapper();
    }
    @Bean
    public OfferRepository offerRepository(
            JpaOfferRepositoryClient jpaOfferRepositoryClient,
            JpaOfferMapper jpaOfferMapper) {
        return new OfferRepositoryProvider(jpaOfferRepositoryClient, jpaOfferMapper);
    }
}
