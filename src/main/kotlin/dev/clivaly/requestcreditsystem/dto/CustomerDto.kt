package dev.clivaly.requestcreditsystem.dto

import dev.clivaly.requestcreditsystem.entity.Address
import dev.clivaly.requestcreditsystem.entity.Customer
import java.math.BigDecimal

data class CustomerDto(
    val fistName: String,
    val lastName: String,
    val cpf: String,
    val income: BigDecimal,
    val email: String,
    val password: String,
    val zipCode: String,
    val street: String,
) {
    fun toEntity(): Customer = Customer(
        firstName = this.fistName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipcode = this.zipCode,
            street = this.street
        )
    )
}