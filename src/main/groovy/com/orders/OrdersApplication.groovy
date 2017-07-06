package com.orders

import com.orders.models.Customer
import com.orders.models.Item
import com.orders.models.Order
import com.orders.models.Payment
import com.orders.repositories.OrderRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters

@EntityScan(
        basePackageClasses = [OrdersApplication.class, Jsr310JpaConverters.class]
)
@SpringBootApplication
@SuppressWarnings("GrMethodMayBeStatic") // @Bean annotated methods should not be static in order to allow correct wrapping by Spring
class OrdersApplication {

    static void main(String[] args) {
        SpringApplication.run OrdersApplication, args
    }

    @SuppressWarnings("GroovyUnusedDeclaration") // Method is used by Spring
    @Bean
    CommandLineRunner setup(OrderRepository orderRepository) {
        { args ->
            orderRepository.save(new Order(
                    customer: new Customer(),
                    itemsPurchased: [
                            new Item(
                                    name: "book",
                                    amount: 40.0
                            ),
                            new Item(
                                    name: "rechargeable batteries",
                                    amount: 60.0
                            )
                    ],
                    paymentsReceived: [new Payment(amount: 100)],
                    totalAmount: 100
            ))
        }
    }
}
