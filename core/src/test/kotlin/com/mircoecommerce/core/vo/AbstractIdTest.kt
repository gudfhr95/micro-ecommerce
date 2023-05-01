package com.mircoecommerce.core.vo

import com.microecommerce.core.vo.AbstractId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkStatic
import java.util.*

class AbstractIdTest : ShouldSpec({

    mockkStatic(UUID::class)

    afterAny {
        clearAllMocks()
    }

    context("Id") {
        context("constructor") {
            should("create id") {
                every { UUID.randomUUID() } returns UUID(0, 0)

                val id = AbstractId()

                id.id shouldBe "00000000-0000-0000-0000-000000000000"
            }

            should("throw exception when id is blank") {
                shouldThrow<IllegalArgumentException> {
                    AbstractId("")
                }
            }
        }

        context("equals") {
            should("return true when ids are equal") {
                every { UUID.randomUUID() } returns UUID(0, 0)

                val id1 = AbstractId()
                val id2 = AbstractId()

                id1 shouldBeEqual id2
            }

            should("return false when ids are not equal") {
                val id1 = AbstractId()
                val id2 = AbstractId()

                id1 shouldNotBeEqual id2
            }
        }
    }
})