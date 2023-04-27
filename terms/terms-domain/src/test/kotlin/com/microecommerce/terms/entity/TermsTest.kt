package com.microecommerce.terms.entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class TermsTest : ShouldSpec({

    context("Terms") {
        should("be created") {
            val terms = Terms("title", "content")

            terms.title shouldBe "title"
            terms.content shouldBe "content"
        }

        context("title") {
            should("not be blank") {
                shouldThrow<IllegalArgumentException> {
                    Terms("", "content")
                }
            }
        }

        context("content") {
            should("not be blank") {
                shouldThrow<IllegalArgumentException> {
                    Terms("title", "")
                }
            }
        }
    }
})
