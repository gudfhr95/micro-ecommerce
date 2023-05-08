package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.DeleteTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import com.microecommerce.terms.service.TermsCommandService
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify

class TermsCommandApplicationServiceTest : ShouldSpec({

    val termsCommandRepository = mockk<TermsCommandRepository>()
    val termsCommandService = TermsCommandService(termsCommandRepository)
    val sut = TermsCommandApplicationService(
        termsCommandService,
        termsCommandRepository
    )

    lateinit var terms: Terms

    beforeTest {
        clearAllMocks()

        terms = Terms(
            type = "type",
            title = "title",
            content = "content"
        )
    }

    context("createTerms") {
        should("create terms") {
            every { termsCommandRepository.existsByType(any()) } returns false
            every { termsCommandRepository.save(any()) } returnsArgument 0
            val command = CreateTermsCommand(
                type = "type",
                title = "title",
                content = "content"
            )

            val createdTerms = sut.createTerms(command)

            with(createdTerms) {
                type shouldBe "type"
                title shouldBe "title"
                content shouldBe "content"
            }
        }

        should("throw when terms already exists") {
            every { termsCommandRepository.existsByType(any()) } returns true
            val command = CreateTermsCommand(
                type = "type",
                title = "title",
                content = "content"
            )

            shouldThrow<IllegalStateException> {
                sut.createTerms(command)
            }
        }

        should("throw when type is blank") {
            every { termsCommandRepository.existsByType(any()) } returns false
            val command = CreateTermsCommand(
                type = "",
                title = "title",
                content = "content"
            )

            shouldThrow<IllegalArgumentException> {
                sut.createTerms(command)
            }
        }

        should("throw when title is blank") {
            every { termsCommandRepository.existsByType(any()) } returns false
            val command = CreateTermsCommand(
                type = "type",
                title = "",
                content = "content"
            )

            shouldThrow<IllegalArgumentException> {
                sut.createTerms(command)
            }
        }

        should("throw when content is blank") {
            every { termsCommandRepository.existsByType(any()) } returns false
            val command = CreateTermsCommand(
                type = "type",
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
                type = "type",
                title = "updated title"
            )
            every { termsCommandRepository.findByType(terms.type) } returns terms
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val updatedTerms = sut.updateTerms(command)

            with(updatedTerms) {
                title shouldBe "updated title"
                content shouldBe "content"
            }
        }

        should("update content") {
            val command = UpdateTermsCommand(
                type = "type",
                content = "updated content"
            )
            every { termsCommandRepository.findByType(terms.type) } returns terms
            every { termsCommandRepository.save(any()) } returnsArgument 0

            val updatedTerms = sut.updateTerms(command)

            with(updatedTerms) {
                title shouldBe "title"
                content shouldBe "updated content"
            }
        }

        should("throw when terms not found") {
            val command = UpdateTermsCommand(
                type = "type",
                title = "updated title",
                content = "updated content"
            )
            every { termsCommandRepository.findByType(terms.type) } returns null

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }

        should("throw when title is blank") {
            val command = UpdateTermsCommand(
                type = "type",
                title = "",
                content = "updated content"
            )
            every { termsCommandRepository.findByType(terms.type) } returns terms

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }

        should("throw when content is blank") {
            val command = UpdateTermsCommand(
                type = "type",
                title = "updated title",
                content = ""
            )
            every { termsCommandRepository.findByType(terms.type) } returns terms

            shouldThrow<IllegalArgumentException> {
                sut.updateTerms(command)
            }
        }
    }

    context("deleteTerms") {
        should("delete terms") {
            val command = DeleteTermsCommand("type")
            every { termsCommandRepository.deleteByType(terms.type) } just Runs

            sut.deleteTerms(command)

            verify(exactly = 1) { termsCommandRepository.deleteByType(command.type) }
        }
    }
})
