package com.ms.rr.orderms.factory;

import com.ms.rr.orderms.listener.dto.OrderCreatedEvent;
import com.ms.rr.orderms.listener.dto.OrderItemEvent;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEventFactory {

    public static OrderCreatedEvent build() {
        var itens = new OrderItemEvent("Notebook", 1, BigDecimal.valueOf(20.50));
        var event = new OrderCreatedEvent(1L, 2L, List.of(itens));
        return event;
    }
}
