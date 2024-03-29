package dev.clivaly.request.credit.system.repository

import dev.clivaly.request.credit.system.entity.Address
import dev.clivaly.request.credit.system.entity.Credit
import dev.clivaly.request.credit.system.entity.Customer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepository: CreditRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: dev.clivaly.request.credit.system.entity.Customer
    private lateinit var credit1: dev.clivaly.request.credit.system.entity.Credit
    private lateinit var credit2: dev.clivaly.request.credit.system.entity.Credit

    @BeforeEach fun setup () {
        customer = testEntityManager.persist(buildCustomer())
        credit1 = testEntityManager.persist(buildCredit(customer = customer))
        credit2 = testEntityManager.persist(buildCredit(customer = customer))
    }

    @Test
    fun `should find credit by credit code`() {
        //given
        val creditCode1 = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val creditCode2 = UUID.fromString("49f740be-46a7-449b-84e7-ff5b7986d7ef")
        credit1.creditCode = creditCode1
        credit2.creditCode = creditCode2
        //when
        val fakeCredit1: dev.clivaly.request.credit.system.entity.Credit = creditRepository.findByCreditCode(creditCode1)!!
        val fakeCredit2: dev.clivaly.request.credit.system.entity.Credit = creditRepository.findByCreditCode(creditCode2)!!
        //then
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)
    }

    @Test
    fun `should find all credits by customer id`() {
        //given
        val customerId: Long =  1L
        //when
        val creditList: List<dev.clivaly.request.credit.system.entity.Credit> = creditRepository.findAllByCustomerId(customerId)
        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit1, credit2)
    }

    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallments: Int = 5,
        customer: dev.clivaly.request.credit.system.entity.Customer
    ): dev.clivaly.request.credit.system.entity.Credit = dev.clivaly.request.credit.system.entity.Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallments = numberOfInstallments,
        customer = customer
    )
    private fun buildCustomer(
        firstName: String = "Jhon",
        lastName: String = "Don",
        cpf: String = "28475934625",
        email: String = "jhon@jhon.com",
        password: String = "12345",
        zipCode: String = "000000",
        street: String = "Rua do Jhon",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
    ) = dev.clivaly.request.credit.system.entity.Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = dev.clivaly.request.credit.system.entity.Address(
            zipCode = zipCode,
            street = street,
        ),
        income = income,
    )

}