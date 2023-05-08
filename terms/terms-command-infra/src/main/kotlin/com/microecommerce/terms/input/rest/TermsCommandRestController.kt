package com.microecommerce.terms.input.rest

import com.microecommerce.terms.application.TermsCommandApplicationService
import com.microecommerce.terms.input.rest.mapper.TermsRequestMapper
import com.microecommerce.terms.input.rest.mapper.TermsResponseMapper
import com.microecommerce.terms.openapi.api.TermsCommandApi
import com.microecommerce.terms.openapi.model.CreateTermsRequest
import com.microecommerce.terms.openapi.model.TermsResponse
import com.microecommerce.terms.openapi.model.UpdateTermsRequest
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

    override fun termsTypePatch(
        type: String,
        updateTermsRequest: UpdateTermsRequest
    ): ResponseEntity<TermsResponse> {
        val terms = termsCommandApplicationService.updateTerms(
            TermsRequestMapper.toCommand(type, updateTermsRequest)
        )

        return ResponseEntity.ok(
            TermsResponseMapper.fromEntity(terms)
        )
    }

    override fun termsTypeDelete(type: String): ResponseEntity<Unit> {
        termsCommandApplicationService.deleteTerms(
            TermsRequestMapper.toCommand(type)
        )

        return ResponseEntity.noContent().build()
    }
}
