package dev.clivaly.requestcreditsystem.repository

import dev.clivaly.requestcreditsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CreditRepository : JpaRepository<Credit, Long> {
    fun findByCreditCode(credit: UUID): Credit?

    @Query(value = "SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}