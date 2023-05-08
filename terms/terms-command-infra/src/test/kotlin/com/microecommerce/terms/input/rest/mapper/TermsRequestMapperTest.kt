package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.openapi.model.CreateTermsRequest
import com.microecommerce.terms.openapi.model.UpdateTermsRequest
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TermsRequestMapperTest : ShouldSpec({

    should("map CreateTermsCommand from request") {
        val request = CreateTermsRequest(
            type = "type",
            title = "title",
            content = "content"
        )

        val command = TermsRequestMapper.toCommand(request)

        command.type shouldBe "type"
        command.title shouldBe "title"
        command.content shouldBe "content"
    }

    should("map UpdateTermsCommand from request") {
        val request = UpdateTermsRequest(
            title = "title",
            content = "content"
        )

        val command = TermsRequestMapper.toCommand("type", request)

        command.type shouldBe "type"
        command.title shouldBe "title"
        command.content shouldBe "content"
    }

    should("map DeleteTermsCommand from request") {
        val command = TermsRequestMapper.toCommand("type")

        command.type shouldBe "type"
    }
})
