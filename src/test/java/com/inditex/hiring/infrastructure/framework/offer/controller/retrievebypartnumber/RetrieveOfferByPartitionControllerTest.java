package com.inditex.hiring.infrastructure.framework.offer.controller.retrievebypartnumber;

import com.inditex.hiring.infrastructure.framework.offer.controller.dto.HttpOfferByPartNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.inditex.hiring.OfferFixtures.ANY_BRAND_ID;
import static com.inditex.hiring.OfferFixtures.ANY_OFFER_BY_PART_NUMBER_LIST;
import static com.inditex.hiring.OfferFixtures.ANY_PRODUCT_PART_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;

class RetrieveOfferByPartitionControllerTest {
    private RetrieveOfferByPartitionController retrieveOfferByPartitionController =
            new RetrieveOfferByPartitionController(null, null);

    @Test
    void should_return_a_list_of_offers_by_brand_and_partition() {
        //Given
        mock_service_get_offer_list_by_brand_and_partition();
        //When
        List<HttpOfferByPartNumber> response =
                retrieveOfferByPartitionController.getOfferByPartNumber(
                        ANY_BRAND_ID, ANY_PRODUCT_PART_NUMBER
                );
        //Then
        assertThat(response).isEqualTo(ANY_OFFER_BY_PART_NUMBER_LIST);
    }

    private void mock_service_get_offer_list_by_brand_and_partition() {

    }
}