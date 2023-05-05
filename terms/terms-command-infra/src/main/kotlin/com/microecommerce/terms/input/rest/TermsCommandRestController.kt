package com.microecommerce.terms.input.rest

import com.microecommerce.terms.application.TermsCommandApplicationService
import com.microecommerce.terms.input.rest.mapper.TermsRequestMapper
import com.microecommerce.terms.input.rest.mapper.TermsResponseMapper
import com.microecommerce.terms.openapi.api.TermsCommandApi
import com.microecommerce.terms.openapi.model.CreateTermsRequest
import com.microecommerce.terms.openapi.model.TermsResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class TermsCommandRestController(
    private val termsCommandApplicationService: TermsCommandApplicationService
) : TermsCommandApi {

    override fun termsPost(createTermsRequest: CreateTermsRequest): ResponseEntity<TermsResponse> {
        val terms = termsCommandApplicationService.createTerms(
            TermsRequestMapper.toCommand(createTermsRequest)
        )

        return ResponseEntity.ok(
            TermsResponseMapper.fromEntity(terms)
        )
    }
}
