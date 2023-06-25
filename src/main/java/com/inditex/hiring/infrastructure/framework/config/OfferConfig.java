package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferConfig {

    @Bean
    public RetrieveOfferService retrieveOfferService() {
        return new RetrieveOfferService();
    }

    @Bean
    public OffsetDateTimeHandler offsetDateTimeHandler() {
        return new OffsetDateTimeHandler();
    }
}
