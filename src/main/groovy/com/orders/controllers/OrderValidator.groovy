package com.orders.controllers

import com.orders.models.Order
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class OrderValidator implements Validator {
    @Override
    boolean supports(Class<?> clazz) {
        return Order.class == clazz
    }

    @Override
    void validate(Object object, Errors errors) {

        Order order = (Order) object

        if (!order.itemsPurchased) {
            errors.reject("itemsPurchased", "order.dont.have.items")
        }

        def totalItemsAmount = order.itemsPurchased.collect { item -> item.amount }.sum()

        if (order.totalAmount != totalItemsAmount) {
            errors.reject(
                    "amounts",
                    "order.total.amounts.dont.match")
        }
    }
}
