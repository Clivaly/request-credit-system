package dev.clivaly.request.credit.system.service.impl

import dev.clivaly.request.credit.system.entity.Customer
import dev.clivaly.request.credit.system.exception.BusinessException
import dev.clivaly.requestcreditsystem.repository.CustomerRepository
import dev.clivaly.requestcreditsystem.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(private val customerRepository: CustomerRepository) : ICustomerService {
    override fun save(customer: dev.clivaly.request.credit.system.entity.Customer): dev.clivaly.request.credit.system.entity.Customer =
        this.customerRepository.save(customer)

    override fun findById(id: Long): dev.clivaly.request.credit.system.entity.Customer = this.customerRepository.findById(id)
        .orElseThrow {
            throw dev.clivaly.request.credit.system.exception.BusinessException("Id $id not found")
        }

    override fun delete(id: Long) {
        val customer: dev.clivaly.request.credit.system.entity.Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}