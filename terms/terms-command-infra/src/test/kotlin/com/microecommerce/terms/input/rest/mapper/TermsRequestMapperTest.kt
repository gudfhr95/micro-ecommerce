package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.openapi.model.CreateTermsRequest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TermsRequestMapperTest : ShouldSpec({

    should("map command from request") {
        val request = CreateTermsRequest(
            title = "title",
            content = "content"
        )

        val command = TermsRequestMapper.toCommand(request)

        command.title shouldBe "title"
        command.content shouldBe "content"
    }
})
