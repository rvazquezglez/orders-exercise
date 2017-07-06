package com.orders.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "PAYMENTS")
class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    LocalDate date
    double amount
}
