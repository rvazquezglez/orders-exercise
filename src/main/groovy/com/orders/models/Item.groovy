package com.orders.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ITEMS")
class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String name
    double amount
}
