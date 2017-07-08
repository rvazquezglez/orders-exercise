package com.orders.controllers

import com.orders.models.Order
import com.orders.repositories.OrderRepository
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Profile("controllerTest")
@Configuration
class ControllerTestConfiguration {

    @Bean
    @Primary
    OrderRepository orderRepository(){
        Mockito.mock(OrderRepository.class)
    }

}
