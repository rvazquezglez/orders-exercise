package com.orders.services

import com.orders.models.Order
import com.orders.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class OrderService {

    @Autowired
    OrderRepository orderRepository

    @CacheEvict(value = "orders", allEntries = true)
    Order save(Order order) {
        orderRepository.save(order)
    }

    @Cacheable(value = "orders")
    List<Order> findAll() {
        orderRepository.findAll()
    }

}
