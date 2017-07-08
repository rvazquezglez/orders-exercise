package com.orders.controllers

import org.springframework.validation.ObjectError

class InvalidOrderException extends Exception {
    List<ObjectError> errorList
}
