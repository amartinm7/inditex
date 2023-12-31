package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.application.offer.create.CreateOfferService;
import com.inditex.hiring.application.offer.deleteall.DeleteAllOffersService;
import com.inditex.hiring.application.offer.deletebyid.DeleteOfferService;
import com.inditex.hiring.application.offer.retrieveall.RetrieveAllOffersService;
import com.inditex.hiring.application.offer.retrievebyid.RetrieveOfferService;
import com.inditex.hiring.application.offer.retrievebypartnumber.RetrieveOfferByPartNumberService;
import com.inditex.hiring.domain.offer.port.OfferRepository;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferByPartNumberMapper;
import com.inditex.hiring.infrastructure.framework.offer.controller.mapper.HttpOfferMapper;
import com.inditex.hiring.infrastructure.framework.offer.repository.FindOffersByIntervalsRowMapper;
import com.inditex.hiring.infrastructure.framework.offer.repository.JdbcOfferRepositoryClient;
import com.inditex.hiring.infrastructure.framework.offer.repository.JpaOfferMapper;
import com.inditex.hiring.infrastructure.framework.offer.repository.JpaOfferRepositoryClient;
import com.inditex.hiring.infrastructure.framework.offer.repository.OfferRepositoryProvider;
import com.inditex.hiring.infrastructure.service.OffsetDateTimeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

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
    public RetrieveOfferByPartNumberService retrieveOfferByPartitionService(
            OfferRepository offerRepository
    ) {
        return new RetrieveOfferByPartNumberService(offerRepository);
    }

    @Bean
    public DeleteOfferService deleteOfferService(OfferRepository offerRepository) {
        return new DeleteOfferService(offerRepository);
    }

    @Bean
    public DeleteAllOffersService deleteAllOffersService(OfferRepository offerRepository) {
        return new DeleteAllOffersService(offerRepository);
    }

    @Bean
    public CreateOfferService createOfferService(OfferRepository offerRepository) {
        return new CreateOfferService(offerRepository);
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
    public HttpOfferByPartNumberMapper httpOfferByPartNumberMapper(
            OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        return new HttpOfferByPartNumberMapper(offsetDateTimeHandler);
    }

    @Bean
    public JpaOfferMapper jpaOfferMapper() {
        return new JpaOfferMapper();
    }

    @Bean
    public FindOffersByIntervalsRowMapper findOffersByIntervalsRowMapper(
            OffsetDateTimeHandler offsetDateTimeHandler
    ) {
        return new FindOffersByIntervalsRowMapper(offsetDateTimeHandler);
    }

    @Bean
    public JdbcOfferRepositoryClient jdbcOfferRepositoryClient(
            NamedParameterJdbcTemplate jdbcTemplate,
            JpaOfferMapper jpaOfferMapper,
            FindOffersByIntervalsRowMapper findOffersByIntervalsRowMapper
    ) {
        return new JdbcOfferRepositoryClient(jdbcTemplate, jpaOfferMapper, findOffersByIntervalsRowMapper);
    }

    @Bean
    public OfferRepository offerRepository(
            JpaOfferRepositoryClient jpaOfferRepositoryClient,
            JpaOfferMapper jpaOfferMapper,
            JdbcOfferRepositoryClient jdbcOfferRepositoryClient
        ) {
        return new OfferRepositoryProvider(jpaOfferRepositoryClient, jpaOfferMapper, jdbcOfferRepositoryClient);
    }
}
