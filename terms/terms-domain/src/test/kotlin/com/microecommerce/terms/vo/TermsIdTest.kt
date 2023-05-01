package com.microecommerce.terms.vo

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkStatic
import java.util.*

class TermsIdTest : ShouldSpec({

    mockkStatic(UUID::class)

    beforeTest {
        clearAllMocks()
    }

    context("TermsId") {
        context("constructor") {
            should("create id") {
                every { UUID.randomUUID() } returns UUID(0, 0)

                val id = TermsId()

                id.id shouldBe "00000000-0000-0000-0000-000000000000"
            }
        }

        context("equals") {
            should("return true when ids are equal") {
                every { UUID.randomUUID() } returns UUID(0, 0)

                val id1 = TermsId()
                val id2 = TermsId()

                id1 shouldBeEqual id2
            }

            should("return false when ids are not equal") {
                val id1 = TermsId()
                val id2 = TermsId()

                id1 shouldNotBeEqual id2
            }
        }
    }
})
