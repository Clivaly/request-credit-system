package dev.clivaly.request.credit.system.dto

import dev.clivaly.request.credit.system.entity.Credit
import dev.clivaly.request.credit.system.entity.Customer
import dev.clivaly.request.credit.system.enummeration.Status
import java.math.BigDecimal
import java.util.UUID

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int,
    val status: dev.clivaly.request.credit.system.enummeration.Status,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?,
) {

    constructor(credit: dev.clivaly.request.credit.system.entity.Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )
}
