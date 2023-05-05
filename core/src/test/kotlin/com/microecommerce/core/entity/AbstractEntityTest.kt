package com.microecommerce.core.entity

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual

class AbstractEntityTest : ShouldSpec({

    context("equals") {
        should("return true when ids are equal") {
            val entity1 = createFixture(1L)
            val entity2 = createFixture(1L)

            entity1 shouldBeEqual entity2
        }

        should("return false when ids are not equal") {
            val entity1 = createFixture(1L)
            val entity2 = createFixture(2L)

            entity1 shouldNotBeEqual entity2
        }
    }
})

fun createFixture(id: Long) = AbstractEntity(
    id = id,
    createdAt = null,
    createdBy = null,
    updatedAt = null,
    updatedBy = null
)
