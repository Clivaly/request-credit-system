package dev.clivaly.request.credit.system.repository

import dev.clivaly.request.credit.system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CreditRepository : JpaRepository<dev.clivaly.request.credit.system.entity.Credit, Long> {
    fun findByCreditCode(credit: UUID): dev.clivaly.request.credit.system.entity.Credit?

    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<dev.clivaly.request.credit.system.entity.Credit>
}