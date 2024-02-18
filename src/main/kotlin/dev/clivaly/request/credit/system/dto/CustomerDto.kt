package dev.clivaly.request.credit.system.dto

import dev.clivaly.request.credit.system.entity.Address
import dev.clivaly.request.credit.system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto(
    @field:NotEmpty(message = "Invalid input, put a valid FirstNme") val firstName: String,
    @field:NotEmpty(message = "Invalid input, put a valid LastName") val lastName: String,
    @field:NotEmpty(message = "Invalid input, put a valid CPF") @CPF(message = "This is not a valid CPF") val cpf: String,
    @field:NotNull(message = "Invalid input, put a valid income") val income: BigDecimal,
    @field:NotEmpty(message = "Invalid input, put a valid email") @Email(message = "This is not a valid E-mail") val email: String,
    @field:NotEmpty(message = "Invalid input, put a valid password") val password: String,
    @field:NotEmpty(message = "Invalid input, put a valid ZipCode") val zipCode: String,
    @field:NotEmpty(message = "Invalid input, put a valid Street") val street: String,
) {
    fun toEntity(): dev.clivaly.request.credit.system.entity.Customer =
        dev.clivaly.request.credit.system.entity.Customer(
            firstName = this.firstName,
            lastName = this.lastName,
            cpf = this.cpf,
            income = this.income,
            email = this.email,
            password = this.password,
            address = dev.clivaly.request.credit.system.entity.Address(
                zipCode = this.zipCode,
                street = this.street
            )
        )
}