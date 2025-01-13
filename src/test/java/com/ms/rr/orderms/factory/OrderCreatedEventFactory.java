package com.ms.rr.orderms.factory;

import com.ms.rr.orderms.listener.dto.OrderCreatedEvent;
import com.ms.rr.orderms.listener.dto.OrderItemEvent;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OrderCreatedEventFactory {

    public static OrderCreatedEvent buildWithOneItem() {
        var itens = new OrderItemEvent("Notebook", 1, BigDecimal.valueOf(20.50));
        var event = new OrderCreatedEvent(1L, 2L, List.of(itens));
        return event;
    }

    public static OrderCreatedEvent buildWithTwoItems() {
        var item1 = new OrderItemEvent("Notebook", 1, BigDecimal.valueOf(20.50));
        var item2 = new OrderItemEvent("Mouse", 1, BigDecimal.valueOf(35.25));

        var event = new OrderCreatedEvent(1L, 2L, List.of(item1, item2));

        return event;
    }
}
