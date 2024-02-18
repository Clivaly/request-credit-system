package dev.clivaly.request.credit.system.service.impl

import dev.clivaly.request.credit.system.entity.Credit
import dev.clivaly.request.credit.system.exception.BusinessException
import dev.clivaly.requestcreditsystem.repository.CreditRepository
import dev.clivaly.requestcreditsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {
    override fun save(credit: dev.clivaly.request.credit.system.entity.Credit): dev.clivaly.request.credit.system.entity.Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<dev.clivaly.request.credit.system.entity.Credit> =
        this.creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): dev.clivaly.request.credit.system.entity.Credit {
        val credit: dev.clivaly.request.credit.system.entity.Credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw dev.clivaly.request.credit.system.exception.BusinessException("CreditCode $creditCode no found")
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact admin")
//        Forma tradicional e If / Else
//        if (credit.customer?.id == customerId) {
//            return credit
//        } else {
//            throw RuntimeException("Contact admin")
//        }
    }


}