package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
import com.microecommerce.terms.openapi.model.CreateTermsRequest
import com.microecommerce.terms.openapi.model.UpdateTermsRequest

object TermsRequestMapper {

    fun toCommand(createTermsRequest: CreateTermsRequest) = CreateTermsCommand(
        type = createTermsRequest.type,
        title = createTermsRequest.title,
        content = createTermsRequest.content
    )

    fun toCommand(
        type: String,
        updateTermsRequest: UpdateTermsRequest
    ) = UpdateTermsCommand(
        type = type,
        title = updateTermsRequest.title,
        content = updateTermsRequest.content
    )
}
