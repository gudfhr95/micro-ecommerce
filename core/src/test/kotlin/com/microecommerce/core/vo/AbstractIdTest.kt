package com.microecommerce.core.vo

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual

class AbstractIdTest : ShouldSpec({

    context("equals") {
        should("return true when ids are equal") {
            val id1 = AbstractId("1")
            val id2 = AbstractId("1")

            id1 shouldBeEqual id2
        }

        should("return false when ids are not equal") {
            val id1 = AbstractId("1")
            val id2 = AbstractId("2")

            id1 shouldNotBeEqual id2
        }
    }
})
