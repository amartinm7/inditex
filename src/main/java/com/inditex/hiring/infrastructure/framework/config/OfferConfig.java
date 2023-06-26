package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.domain.offer.OfferRepository;
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
    public OffsetDateTimeHandler offsetDateTimeHandler() {
        return new OffsetDateTimeHandler();
    }

    @Bean
    public OfferRepository offerRepository(JpaOfferRepositoryClient jpaOfferRepositoryClient) {
        return new OfferRepositoryProvider(jpaOfferRepositoryClient);
    }
}
