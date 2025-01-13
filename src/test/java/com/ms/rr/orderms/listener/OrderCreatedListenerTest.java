package com.ms.rr.orderms.listener;

import com.ms.rr.orderms.factory.OrderCreatedEventFactory;
import com.ms.rr.orderms.listener.dto.OrderCreatedEvent;
import com.ms.rr.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.support.MessageBuilder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderCreatedListenerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderCreatedListener orderCreatedListener;

    @Nested
    class Listen {

        @Test
        void shouldCallServiceWithCorrectParameters() {
            // ARRANGE
            var event = OrderCreatedEventFactory.build();
            var message = MessageBuilder.withPayload(event).build();

            // ACT
            orderCreatedListener.listener(message);

            // ASSERT
            verify(orderService, times(1)).save(eq(message.getPayload()));
        }
    }
}