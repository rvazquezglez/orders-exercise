package com.orders.models

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "ORDERS")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @ManyToOne(cascade = CascadeType.ALL)
    Customer customer

    double totalAmount

    LocalDate date

    @OneToMany(cascade = CascadeType.ALL)
    List<Item> itemsPurchased

    @OneToMany(cascade = CascadeType.ALL)
    List<Payment> paymentsReceived
}
