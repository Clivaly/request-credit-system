package dev.clivaly.request.credit.system.repository

import dev.clivaly.request.credit.system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<dev.clivaly.request.credit.system.entity.Customer, Long> {
}