package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.openapi.model.CreateTermsRequest

object TermsRequestMapper {

    fun toCommand(createTermsRequest: CreateTermsRequest) = CreateTermsCommand(
        title = createTermsRequest.title,
        content = createTermsRequest.content
    )
}
