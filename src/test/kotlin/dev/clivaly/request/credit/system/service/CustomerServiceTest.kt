package dev.clivaly.request.credit.system.service

import dev.clivaly.request.credit.system.repository.CustomerRepository
import dev.clivaly.request.credit.system.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import java.util.*

//@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK
    lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    lateinit var customerService: CustomerService

    @Test
    fun `should create customer`() {
        //given
        val fakeCustomer: dev.clivaly.request.credit.system.entity.Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        //when
        val actual: dev.clivaly.request.credit.system.entity.Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should find customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: dev.clivaly.request.credit.system.entity.Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        //when
        val actual: dev.clivaly.request.credit.system.entity.Customer = customerService.findById(fakeId)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(dev.clivaly.request.credit.system.entity.Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: dev.clivaly.request.credit.system.entity.Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        //when
        //then
        Assertions.assertThatExceptionOfType(dev.clivaly.request.credit.system.exception.BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
    }

    @Test
    fun `should delete customer by id`() {
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: dev.clivaly.request.credit.system.entity.Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer) } just runs
        //when
        customerService.delete(fakeId)
        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) {
            customerRepository.delete(fakeCustomer)
        }
    }

    companion object {
        fun buildCustomer(
            firstName: String = "Jhon",
            lastName: String = "Don",
            cpf: String = "28475934625",
            email: String = "jhon@jhon.com",
            password: String = "12345",
            zipCode: String = "000000",
            street: String = "Rua do Jhon",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = dev.clivaly.request.credit.system.entity.Customer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = dev.clivaly.request.credit.system.entity.Address(
                zipCode = zipCode,
                street = street
            ),
            income = income,
            id = id,
        )
    }
}
