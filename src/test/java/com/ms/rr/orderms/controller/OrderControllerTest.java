package com.ms.rr.orderms.controller;

import com.ms.rr.orderms.factory.OrderResponseFactory;
import com.ms.rr.orderms.service.OrderService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
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

    @Captor
    ArgumentCaptor<Long> customerIdCaptor;

    @Captor
    ArgumentCaptor<PageRequest> pageRequestCaptor;

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
            // ARRANGE - prepara todos os mocks para a execução
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;

            var pageResponse = OrderResponseFactory.buildWithOneItem();
            doReturn(pageResponse).when(orderService).findAllByCustomerId(customerIdCaptor.capture(), pageRequestCaptor.capture());

            BigDecimal totalOnOrders = BigDecimal.valueOf(20.50);
            doReturn(totalOnOrders).when(orderService).findTotalOnOrdersByCustomerId(customerIdCaptor.capture());

            // ACT - executar o método a ser testado
            var response = orderController.listOrders(customerId, page, pageSize);

            // ASSERT - verifica se a execução foi correta
            assertEquals(2, customerIdCaptor.getAllValues().size());
            assertEquals(customerId, customerIdCaptor.getAllValues().get(0));
            assertEquals(customerId, customerIdCaptor.getAllValues().get(1));
            assertEquals(page, pageRequestCaptor.getValue().getPageNumber());
            assertEquals(pageSize, pageRequestCaptor.getValue().getPageSize());
        }

        @Test
        void shouldReturnResponseBodyCorrectly() {
            // ARRANGE - prepara todos os mocks para a execução
            var customerId = 1L;
            var page = 0;
            var pageSize = 10;
            var pagination = OrderResponseFactory.buildWithOneItem();
            BigDecimal totalOnOrders = BigDecimal.valueOf(20.50);

            doReturn(pagination).when(orderService).findAllByCustomerId(anyLong(), any());
            doReturn(totalOnOrders).when(orderService).findTotalOnOrdersByCustomerId(any());

            // ACT - executar o método a ser testado
            var response = orderController.listOrders(customerId, page, pageSize);

            // ASSERT - verifica se a execução foi correta
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertNotNull(response.getBody().data());
            assertNotNull(response.getBody().pagination());
            assertNotNull(response.getBody().summary());

            assertEquals(totalOnOrders, response.getBody().summary().get("totalOnOrders"));

            assertEquals(pagination.getTotalElements(), response.getBody().pagination().totalElements());
            assertEquals(pagination.getTotalPages(), response.getBody().pagination().totalPages());
            assertEquals(pagination.getNumber(), response.getBody().pagination().page());
            assertEquals(pagination.getSize(), response.getBody().pagination().pageSize());

            assertEquals(pagination.getContent(), response.getBody().data());
        }
    }

}