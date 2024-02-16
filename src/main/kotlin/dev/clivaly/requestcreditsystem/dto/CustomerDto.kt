package dev.clivaly.requestcreditsystem.dto

import dev.clivaly.requestcreditsystem.entity.Address
import dev.clivaly.requestcreditsystem.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Invalid input, put a valid FirstNme") val fistName: String,
    @field:NotEmpty(message = "Invalid input, put a valid LastName") val lastName: String,
    @field:NotEmpty(message = "Invalid input, put a valid CPF") @CPF(message = "This is not a valid CPF") val cpf: String,
    @field:NotNull(message = "Invalid input, put a valid income") val income: BigDecimal,
    @field:NotEmpty(message = "Invalid input, put a valid email") @Email(message = "This is not a valid E-mail") val email: String,
    @field:NotEmpty(message = "Invalid input, put a valid password") val password: String,
    @field:NotEmpty(message = "Invalid input, put a valid ZipCode") val zipCode: String,
    @field:NotEmpty(message = "Invalid input, put a valid Street") val street: String,
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