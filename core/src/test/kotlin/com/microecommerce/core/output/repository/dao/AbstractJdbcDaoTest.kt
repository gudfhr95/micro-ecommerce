package com.microecommerce.core.output.repository.dao

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class AbstractJdbcDaoTest : ShouldSpec({

    context("AbstractJdbcDao") {
        val dao = AbstractJdbcDao()

        should("be able to delete") {
            dao.delete()

            dao.isDeleted shouldBe true
        }
    }
})
