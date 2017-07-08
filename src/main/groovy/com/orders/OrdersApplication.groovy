package com.orders

import com.orders.controllers.OrderAmountsValidator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters
import org.springframework.validation.Validator

@EntityScan(
        basePackageClasses = [OrdersApplication.class, Jsr310JpaConverters.class]
)
@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@SuppressWarnings("GrMethodMayBeStatic") // @Bean annotated methods should not be static in order to allow correct wrapping by Spring
class OrdersApplication {

    static void main(String[] args) {
        SpringApplication.run OrdersApplication, args
    }

    @Bean
    List<Validator> validatorList(){
        [new OrderAmountsValidator()]
    }
}
