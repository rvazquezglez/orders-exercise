package com.orders.controllers

import com.orders.models.Order
import com.orders.repositories.OrderRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class OrderController {

    @Autowired
    OrderRepository orderRepository

    @Autowired
    OrderValidator orderValidator

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    def getAllOrders() {
        log.info("Getting all orders")
        new ResponseEntity<List<Order>>(orderRepository.findAll(), HttpStatus.OK)
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    def saveOrder(@RequestBody Order order, BindingResult bindingResult) {

        orderValidator.validate(order, bindingResult)

        if (bindingResult.hasErrors()) {
            throw new InvalidOrderException(errorList: bindingResult.getAllErrors())
        } else {
            log.info("Saving {}", order)
            new ResponseEntity<Order>(orderRepository.save(order), HttpStatus.OK)
        }
    }

    @ExceptionHandler(InvalidOrderException.class)
    def handleInvalidOrder(InvalidOrderException ex) {
        new ResponseEntity<String>("Invalid order. " + ex.errorList, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}
