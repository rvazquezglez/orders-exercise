package com.orders.controllers

import com.orders.models.Order
import com.orders.services.OrderService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.Validator
import org.springframework.web.bind.annotation.*

import javax.annotation.Resource

@RestController
@Slf4j
@SuppressWarnings("GroovyUnusedDeclaration")
class OrderController {

    @Autowired
    OrderService orderService

    @Resource(name = 'validatorList')
    List<Validator> validatorList

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    def getAllOrders() {
        log.info("Getting all orders")
        new ResponseEntity<List<Order>>(orderService.findAll(), HttpStatus.OK)
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    def saveOrder(@RequestBody Order order, BindingResult bindingResult) {

        validatorList.forEach({ validator -> validator.validate(order, bindingResult) })

        if (bindingResult.hasErrors()) {
            throw new InvalidOrderException(errorList: bindingResult.getAllErrors())
        } else {
            log.info("Saving {}", order)
            new ResponseEntity<Order>(orderService.save(order), HttpStatus.OK)
        }
    }

    @ExceptionHandler(InvalidOrderException.class)
    @SuppressWarnings("GrMethodMayBeStatic")
    def handleInvalidOrder(InvalidOrderException ex) {
        new ResponseEntity<String>("Invalid order. " + ex.errorList, HttpStatus.UNPROCESSABLE_ENTITY)
    }

}
