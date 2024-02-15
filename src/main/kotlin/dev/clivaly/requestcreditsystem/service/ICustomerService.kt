package dev.clivaly.requestcreditsystem.service;

import dev.clivaly.requestcreditsystem.entity.Customer

public interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)
}
