package dev.clivaly.request.credit.system.controller

import dev.clivaly.request.credit.system.dto.CustomerDto
import dev.clivaly.request.credit.system.dto.CustomerUpdateDto
import dev.clivaly.request.credit.system.dto.CustomerView
import dev.clivaly.request.credit.system.entity.Customer
import dev.clivaly.requestcreditsystem.service.impl.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerResource(
    private val customerService: CustomerService
) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: dev.clivaly.request.credit.system.dto.CustomerDto): ResponseEntity<dev.clivaly.request.credit.system.dto.CustomerView> {
        val savedCustomer: dev.clivaly.request.credit.system.entity.Customer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            dev.clivaly.request.credit.system.dto.CustomerView(
                savedCustomer
            )
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<dev.clivaly.request.credit.system.dto.CustomerView> {
        val customer: dev.clivaly.request.credit.system.entity.Customer = this.customerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(dev.clivaly.request.credit.system.dto.CustomerView(customer))
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDto: dev.clivaly.request.credit.system.dto.CustomerUpdateDto
    ): ResponseEntity<dev.clivaly.request.credit.system.dto.CustomerView> {
        val customer: dev.clivaly.request.credit.system.entity.Customer = this.customerService.findById(id)
        val customerToUpdate: dev.clivaly.request.credit.system.entity.Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated: dev.clivaly.request.credit.system.entity.Customer = this.customerService.save(customerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(
            dev.clivaly.request.credit.system.dto.CustomerView(
                customerUpdated
            )
        )

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) = this.customerService.delete(id)
}