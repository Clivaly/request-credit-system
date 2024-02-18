package dev.clivaly.request.credit.system.entity

import dev.clivaly.request.credit.system.enummeration.Status
import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@Entity
@Table(name = "Credit")
class Credit (
    @Column(nullable = false, unique = true) var creditCode: UUID = UUID.randomUUID(),
    @Column(nullable = false) val creditValue: BigDecimal = BigDecimal.ZERO,
    @Column(nullable = false) val dayFirstInstallment: LocalDate,
    @Column(nullable = false) val numberOfInstallments: Int = 0,
    @Enumerated val status: dev.clivaly.request.credit.system.enummeration.Status = dev.clivaly.request.credit.system.enummeration.Status.IN_PROGRESS,
    @ManyToOne var customer: dev.clivaly.request.credit.system.entity.Customer? = null,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null
)
