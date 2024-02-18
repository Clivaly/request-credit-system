package dev.clivaly.request.credit.system.dto

import dev.clivaly.request.credit.system.entity.Credit
import dev.clivaly.request.credit.system.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid input, put a valid value for credit") val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment: LocalDate,
    val numberOfInstallments: Int,
    @field:NotNull(message = "Invalid input, put a valid customer ID") val customerId: Long,
) {
    fun toEntity(): dev.clivaly.request.credit.system.entity.Credit = dev.clivaly.request.credit.system.entity.Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = dev.clivaly.request.credit.system.entity.Customer(id = this.customerId)
    )
}
