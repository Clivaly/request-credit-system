package dev.clivaly.request.credit.system.controller

import dev.clivaly.request.credit.system.dto.CreditDto
import dev.clivaly.request.credit.system.dto.CreditView
import dev.clivaly.request.credit.system.dto.CreditViewList
import dev.clivaly.request.credit.system.entity.Credit
import dev.clivaly.requestcreditsystem.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditResource(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: dev.clivaly.request.credit.system.dto.CreditDto): ResponseEntity<String> {
        val credit: dev.clivaly.request.credit.system.entity.Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.email} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<dev.clivaly.request.credit.system.dto.CreditViewList>> {
        val creditViewList: List<dev.clivaly.request.credit.system.dto.CreditViewList> = this.creditService.findAllByCustomer(customerId)
            .stream()
            .map { credit: dev.clivaly.request.credit.system.entity.Credit ->
                dev.clivaly.request.credit.system.dto.CreditViewList(
                    credit
                )
            }
            .collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun finByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable creditCode: UUID
    ): ResponseEntity<dev.clivaly.request.credit.system.dto.CreditView> {
        val credit: dev.clivaly.request.credit.system.entity.Credit = this.creditService.findByCreditCode(customerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(dev.clivaly.request.credit.system.dto.CreditView(credit))
    }
}