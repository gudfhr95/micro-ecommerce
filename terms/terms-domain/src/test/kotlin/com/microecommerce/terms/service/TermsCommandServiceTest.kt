package com.microecommerce.terms.service

import com.microecommerce.terms.repository.TermsCommandRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class TermsCommandServiceTest : ShouldSpec({

    val termsCommandRepository = mockk<TermsCommandRepository>()
    val termsCommandService = TermsCommandService(termsCommandRepository)

    beforeTest {
        clearAllMocks()
    }

    context("createTerms") {
        should("create terms") {
            every { termsCommandRepository.existsByType(any()) } returns false
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val terms = termsCommandService.createTerms(
                type = "type",
                title = "title",
                content = "content"
            )

            terms.type shouldBe "type"
            terms.title shouldBe "title"
            terms.content shouldBe "content"
        }

        should("throw exception when terms already exists") {
            every { termsCommandRepository.existsByType(any()) } returns true

            val exception = shouldThrow<IllegalStateException> {
                termsCommandService.createTerms(
                    type = "type",
                    title = "title",
                    content = "content"
                )
            }

            exception.message shouldBe "Terms already exists"
        }
    }
})
