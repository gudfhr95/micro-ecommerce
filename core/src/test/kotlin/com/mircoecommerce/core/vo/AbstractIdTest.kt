package com.mircoecommerce.core.vo

import com.microecommerce.core.vo.AbstractId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
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
        context("equals") {
            should("Return true when ids are equal") {
                every { UUID.randomUUID() } returns UUID(0, 0)

                val id1 = AbstractId()
                val id2 = AbstractId()

                id1 shouldBeEqual id2
            }

            should("Return false when ids are not equal") {
                val id1 = AbstractId()
                val id2 = AbstractId()

                id1 shouldNotBeEqual id2
            }
        }
    }
})
