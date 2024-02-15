package dev.clivaly.requestcreditsystem.service;

import dev.clivaly.requestcreditsystem.entity.Credit;

import java.util.UUID;

public interface ICreditService {
    fun save(credit: Credit): Credit

    fun findAllByCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}
