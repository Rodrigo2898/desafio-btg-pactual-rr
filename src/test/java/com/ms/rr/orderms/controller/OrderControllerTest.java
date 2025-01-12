package com.ms.rr.orderms.controller;

import com.ms.rr.orderms.factory.OrderResponseFactory;
import com.ms.rr.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    @Nested // indicando que essa sub-classe também vai conter teste unitários
    class ListOrders {

        @Test
        void shouldReturnHttpOk() {
            // ARRANGE - prepara todos os mocks para a execução
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;

            var pageResponse = OrderResponseFactory.buildWithOneItem();  // Mock da resposta de ordens
            doReturn(pageResponse).when(orderService).findAllByCustomerId(anyLong(), any());

            BigDecimal totalOnOrders = BigDecimal.valueOf(20.50);  // Mock do total
            doReturn(totalOnOrders).when(orderService).findTotalOnOrdersByCustomerId(anyLong());

            // ACT - executar o método a ser testado
            var response = orderController.listOrders(customerId, page, pageSize);

            // ASSERT - verifica se a execução foi correta
            assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        }

        @Test
        void shouldPassCorrectParametersToService() {
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
        }
    }

}