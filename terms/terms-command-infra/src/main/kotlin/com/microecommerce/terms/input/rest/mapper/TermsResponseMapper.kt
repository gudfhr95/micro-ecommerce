package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.openapi.model.TermsResponse

object TermsResponseMapper {

    fun fromEntity(terms: Terms) = TermsResponse(
        termsId = terms.termsId.id,
        type = terms.type,
        title = terms.title,
        content = terms.content,
        version = terms.version.toBigDecimal()
    )
}
