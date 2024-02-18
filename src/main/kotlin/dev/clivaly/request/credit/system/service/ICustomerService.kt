package dev.clivaly.request.credit.system.service;

import dev.clivaly.request.credit.system.entity.Customer

public interface ICustomerService {
    fun save(customer: dev.clivaly.request.credit.system.entity.Customer): dev.clivaly.request.credit.system.entity.Customer

    fun findById(id: Long): dev.clivaly.request.credit.system.entity.Customer

    fun delete(id: Long)
}
