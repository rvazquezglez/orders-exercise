package com.orders.repositories

import com.orders.models.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository("orders")
interface OrderRepository extends JpaRepository<Order, Long> {
}
