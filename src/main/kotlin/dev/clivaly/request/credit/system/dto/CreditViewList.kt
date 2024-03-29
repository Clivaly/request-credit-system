package dev.clivaly.request.credit.system.dto

import dev.clivaly.request.credit.system.entity.Credit
import java.math.BigDecimal
import java.util.UUID

data class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int
) {
    constructor(credit: dev.clivaly.request.credit.system.entity.Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallments
    )
}
