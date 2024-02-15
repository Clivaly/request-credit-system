package dev.clivaly.requestcreditsystem.service.impl

import dev.clivaly.requestcreditsystem.entity.Credit
import dev.clivaly.requestcreditsystem.repository.CreditRepository
import dev.clivaly.requestcreditsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("CreditCode $creditCode no found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
//        Forma tradicional e If / Else
//        if (credit.customer?.id == customerId) {
//            return credit
//        } else {
//            throw RuntimeException("Contact admin")
//        }
    }


}