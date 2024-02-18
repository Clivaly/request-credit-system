package dev.clivaly.request.credit.system.service;

import dev.clivaly.request.credit.system.entity.Credit;

import java.util.UUID;

public interface ICreditService {
    fun save(credit: dev.clivaly.request.credit.system.entity.Credit): dev.clivaly.request.credit.system.entity.Credit

    fun findAllByCustomer(customerId: Long): List<dev.clivaly.request.credit.system.entity.Credit>

    fun findByCreditCode(customerId: Long, creditCode: UUID): dev.clivaly.request.credit.system.entity.Credit
}
