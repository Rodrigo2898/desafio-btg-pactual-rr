package com.ms.rr.orderms.controller.dto;

import com.ms.rr.orderms.factory.OrderEnityFactory;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderResponseTest {

    @Nested
    class FromEntity {

        @Test
        void shouldMapCorrectly() {
            // ARRAGE
            var input = OrderEnityFactory.build();

            // ACT
            var output = OrderResponse.fromEntity(input);

            // ASSERT
            assertEquals(input.getOrderId(), output.orderId());
            assertEquals(input.getCustomerId(), output.customerId());
            assertEquals(input.getTotal(), output.total());
        }
    }
}