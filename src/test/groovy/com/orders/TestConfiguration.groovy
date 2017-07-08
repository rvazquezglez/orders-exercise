package com.orders

import com.orders.models.Order
import com.orders.repositories.OrderRepository
import org.mockito.Mockito
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Profile

@Profile("testProfile")
@Configuration
class TestConfiguration {

    @SuppressWarnings(["GrMethodMayBeStatic", "GroovyUnusedDeclaration"])
    @Bean
    @Primary
    OrderRepository orderRepository(){
        Mockito.mock(OrderRepository.class)
    }

}
