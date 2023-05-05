package com.microecommerce.terms.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TermsTest : ShouldSpec({

    context("Terms") {
        should("be created") {
            val terms = Terms(
                title = "title",
                content = "content"
            )

            terms.title shouldBe "title"
            terms.content shouldBe "content"
        }

        context("title") {
            should("not be blank") {
                shouldThrow<IllegalArgumentException> {
                    Terms(
                        title = "",
                        content = "content"
                    )
                }
            }
        }

        context("content") {
            should("not be blank") {
                shouldThrow<IllegalArgumentException> {
                    Terms(
                        title = "title",
                        content = ""
                    )
                }
            }
        }

        context("update") {
            should("update title") {
                val terms = Terms(
                    title = "title",
                    content = "content"
                )

                terms.update(title = "new title")

                terms.title shouldBe "new title"
            }

            should("update content") {
                val terms = Terms(
                    title = "title",
                    content = "content"
                )

                terms.update(content = "new content")

                terms.content shouldBe "new content"
            }

            should("not update title if blank") {
                val terms = Terms(
                    title = "title",
                    content = "content"
                )

                shouldThrow<IllegalArgumentException> {
                    terms.update(title = "")
                }
            }

            should("not update content if blank") {
                val terms = Terms(
                    title = "title",
                    content = "content"
                )

                shouldThrow<IllegalArgumentException> {
                    terms.update(content = "")
                }
            }
        }
    }
})
