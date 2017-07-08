package com.orders.controllers

import com.orders.models.Order
import org.junit.Before
import org.junit.Test
import org.springframework.validation.ValidationUtils
import org.springframework.validation.BindException

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.hasProperty
import static org.hamcrest.Matchers.is

class OrderValidatorTest {

    OrderValidator orderValidator

    @Before
    void setUp() {
        orderValidator = new OrderValidator()
    }

    @Test
    void testSupports() {
        assertThat(orderValidator.supports(Object.class), is(false))
        assertThat(orderValidator.supports(Order.class), is(true))
    }

    @Test
    void testOrderWithoutItems() {
        def order = new Order()
        def errors = new BindException(order, "order")
        ValidationUtils.invokeValidator(orderValidator, order, errors)

        assertThat(
                errors.getAllErrors(),
                hasItem(hasProperty(
                        "defaultMessage",
                        is("order.dont.have.items"))))
    }

    @Test
    void testOrderAmountsDontMatch() {
        def order = new Order()
        def errors = new BindException(order, "order")
        ValidationUtils.invokeValidator(orderValidator, order, errors)

        assertThat(
                errors.getAllErrors(),
                hasItem(hasProperty(
                        "defaultMessage",
                        is("order.total.amounts.dont.match"))))
    }

    @Test
    void testValidOrder() {
    }

}