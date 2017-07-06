package com.orders.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "CUSTOMERS")
class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
    String lastName
}
