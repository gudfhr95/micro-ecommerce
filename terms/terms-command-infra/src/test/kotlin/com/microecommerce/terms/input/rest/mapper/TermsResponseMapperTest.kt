package com.microecommerce.terms.input.rest.mapper

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.vo.TermsId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TermsResponseMapperTest : ShouldSpec({

    should("map response from terms") {
        val terms = Terms(
            termsId = TermsId("1"),
            type = "type",
            title = "title",
            content = "content"
        )

        val response = TermsResponseMapper.fromEntity(terms)

        response.termsId shouldBe "1"
        response.type shouldBe "type"
        response.title shouldBe "title"
        response.content shouldBe "content"
    }
})
