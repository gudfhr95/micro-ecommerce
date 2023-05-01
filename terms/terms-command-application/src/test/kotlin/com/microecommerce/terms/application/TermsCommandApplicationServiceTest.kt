package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.repository.TermsCommandRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class TermsCommandApplicationServiceTest : ShouldSpec({

    val termsCommandRepository = mockk<TermsCommandRepository>()

    val sut = TermsCommandApplicationService(termsCommandRepository)

    beforeTest {
        clearAllMocks()
    }

    context("createTerms") {
        should("create terms") {
            val command = CreateTermsCommand(
                title = "title",
                content = "content"
            )
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val terms = sut.createTerms(command)

            with(terms) {
                title shouldBe "title"
                content shouldBe "content"
            }
        }

        should("throw when title is blank") {
            val command = CreateTermsCommand(
                title = "",
                content = "content"
            )

            shouldThrow<IllegalArgumentException> {
                sut.createTerms(command)
            }
        }

        should("throw when content is blank") {
            val command = CreateTermsCommand(
                title = "title",
                content = ""
            )

            shouldThrow<IllegalArgumentException> {
                sut.createTerms(command)
            }
        }
    }
})
