package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import com.microecommerce.terms.vo.TermsId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk

class TermsCommandApplicationServiceTest : ShouldSpec({

    val termsCommandRepository = mockk<TermsCommandRepository>()
    val sut = TermsCommandApplicationService(termsCommandRepository)

    lateinit var terms: Terms

    beforeTest {
        clearAllMocks()

        terms = Terms(
            title = "title",
            content = "content"
        )
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

    context("updateTerms") {
        should("update title") {
            val command = UpdateTermsCommand(
                termsId = terms.termsId,
                title = "updated title"
            )
            every { termsCommandRepository.findByTermsId(terms.termsId) } returns terms
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val updatedTerms = sut.updateTerms(command)

            with(updatedTerms) {
                title shouldBe "updated title"
                content shouldBe "content"
            }
        }

        should("update content") {
            val command = UpdateTermsCommand(
                termsId = terms.termsId,
                content = "updated content"
            )
            every { termsCommandRepository.findByTermsId(terms.termsId) } returns terms
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val updatedTerms = sut.updateTerms(command)

            with(updatedTerms) {
                title shouldBe "title"
                content shouldBe "updated content"
            }
        }

        should("throw when terms not found") {
            val command = UpdateTermsCommand(
                termsId = TermsId(),
                title = "updated title",
                content = "updated content"
            )
            every { termsCommandRepository.findByTermsId(command.termsId) } returns null

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }

        should("throw when title is blank") {
            val command = UpdateTermsCommand(
                termsId = terms.termsId,
                title = "",
                content = "updated content"
            )
            every { termsCommandRepository.findByTermsId(terms.termsId) } returns terms

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }

        should("throw when content is blank") {
            val command = UpdateTermsCommand(
                termsId = terms.termsId,
                title = "updated title",
                content = ""
            )
            every { termsCommandRepository.findByTermsId(terms.termsId) } returns terms

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }
    }
})
