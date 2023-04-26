package com.mircoecommerce.core.vo

import com.microecommerce.core.vo.AbstractId
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual

class AbstractIdTest : ShouldSpec({

    context("Id") {
        context("When id is blank") {
            should("Throw exception") {
                shouldThrow<IllegalArgumentException> {
                    AbstractId(id = "")
                }
            }
        }

        context("equals") {
            should("Return true when ids are equal") {
                val id1 = AbstractId(id = "1")
                val id2 = AbstractId(id = "1")

                id1 shouldBeEqual id2
            }

            should("Return false when ids are not equal") {
                val id1 = AbstractId(id = "1")
                val id2 = AbstractId(id = "2")

                id1 shouldNotBeEqual id2
            }
        }
    }
})
