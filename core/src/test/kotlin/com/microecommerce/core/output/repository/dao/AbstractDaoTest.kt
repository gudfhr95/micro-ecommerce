package com.microecommerce.core.output.repository.dao

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.shouldBe

class AbstractDaoTest : ShouldSpec({

    context("delete") {
        should("be able to delete") {
            val dao = AbstractDao()

            dao.delete()

            dao.isDeleted shouldBe true
        }
    }

    context("equals") {
        should("return true when ids are equal") {
            val dao1 = AbstractDao(1L)
            val dao2 = AbstractDao(1L)

            dao1 shouldBeEqual dao2
        }

        should("return false when ids are not equal") {
            val dao1 = AbstractDao(1L)
            val dao2 = AbstractDao(2L)

            dao1 shouldNotBeEqual dao2
        }
    }
})
