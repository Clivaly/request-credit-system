package dev.clivaly.request.credit.system.dto

import dev.clivaly.request.credit.system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Invalid input, put a valid FirstNme") val firstName: String,
    @field:NotEmpty(message = "Invalid input, put a valid LastName") val lastName: String,
    @field:NotNull(message = "Invalid input, put a valid income") val income: BigDecimal,
    @field:NotEmpty(message = "Invalid input, put a valid ZipCode") val zipCode: String,
    @field:NotEmpty(message = "Invalid input, put a valid Street") val street: String,
) {
    fun toEntity(customer: dev.clivaly.request.credit.system.entity.Customer): dev.clivaly.request.credit.system.entity.Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.street = this.street
        customer.address.zipCode = this.zipCode
        return customer
    }
}
