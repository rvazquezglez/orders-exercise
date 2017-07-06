package com.orders.repositories

import com.orders.models.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface OrderRepository extends CrudRepository<Order, Long> {
}
