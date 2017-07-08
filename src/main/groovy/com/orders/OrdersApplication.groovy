package com.orders

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters

@EntityScan(
        basePackageClasses = [OrdersApplication.class, Jsr310JpaConverters.class]
)
@SpringBootApplication
@EnableCaching
@SuppressWarnings("GrMethodMayBeStatic") // @Bean annotated methods should not be static in order to allow correct wrapping by Spring
class OrdersApplication {

    static void main(String[] args) {
        SpringApplication.run OrdersApplication, args
    }
}
