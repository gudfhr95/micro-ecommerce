package com.microecommerce.terms.input.rest

import com.microecommerce.terms.application.TermsCommandApplicationService
import com.microecommerce.terms.input.rest.model.CreateTermsRequest
import com.microecommerce.terms.input.rest.model.TermsResponse
import org.springframework.http.ResponseEntity

class TermsCommandRestController(
    private val termsCommandApplicationService: TermsCommandApplicationService
) : TermsCommandApi {

    override fun termsPost(createTermsRequest: CreateTermsRequest): ResponseEntity<TermsResponse> {
        termsCommandApplicationService
        return super.termsPost(createTermsRequest)
    }
}
