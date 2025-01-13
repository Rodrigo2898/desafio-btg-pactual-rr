package com.ms.rr.orderms.factory;

import com.ms.rr.orderms.entity.OrderEntity;
import com.ms.rr.orderms.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;

public class OrderEnityFactory {

    public static OrderEntity build() {
        var orderEntity = new OrderEntity();
        orderEntity.setOrderId(1L);
        orderEntity.setOrderId(2L);
        orderEntity.setTotal(BigDecimal.valueOf(20.50));
        orderEntity.setItems(items());

        return  orderEntity;
    }

    public static Page<OrderEntity> buildWithPage() {
        return new PageImpl<>(List.of(build()));
    }

    private static List<OrderItem> items() {
        var items = new OrderItem("Notebook", 1, BigDecimal.valueOf(20.50));
        return List.of(items);
    }
}
