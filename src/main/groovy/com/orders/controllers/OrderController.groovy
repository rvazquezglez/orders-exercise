package com.orders.controllers

import com.orders.models.Order
import com.orders.repositories.OrderRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class OrderController {

    @Autowired
    OrderRepository orderRepository

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    def getAllOrders() {
        log.info("Getting all orders")
        new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK)
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    def saveOrder(@RequestBody Order order) {
        log.info("Saving {}", order)
        // TODO: add validation
        new ResponseEntity<Order>(orderRepository.save(order), HttpStatus.OK)
    }
}
