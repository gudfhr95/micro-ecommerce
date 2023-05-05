package com.microecommerce.terms.output.repository.mapper

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.dao.TermsDao
import com.microecommerce.terms.vo.TermsId
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class TermsDaoMapperTest : ShouldSpec({

    val now = LocalDateTime.now()

    context("toEntity") {
        val dao = TermsDao(
            id = 1,
            termsId = "1",
            title = "title",
            content = "content",
            version = 1,
            createdAt = now,
            createdBy = "admin",
            updatedAt = now,
            updatedBy = "admin"
        )

        should("map dao to entity") {
            val entity = TermsDaoMapper.toEntity(dao)

            entity.id shouldBe 1
            entity.version shouldBe 1
            entity.termsId.id shouldBe "1"
            entity.title shouldBe "title"
            entity.content shouldBe "content"
            entity.createdAt shouldBe now
            entity.createdBy shouldBe "admin"
            entity.updatedAt shouldBe now
            entity.updatedBy shouldBe "admin"
        }
    }

    context("toDao") {
        val entity = Terms(
            id = 1,
            termsId = TermsId("1"),
            title = "title",
            content = "content",
            version = 1,
            createdAt = now,
            createdBy = "admin",
            updatedAt = now,
            updatedBy = "admin"
        )

        should("map entity to dao") {
            val dao = TermsDaoMapper.fromEntity(entity)

            dao.id shouldBe 1
            dao.version shouldBe 1
            dao.termsId shouldBe "1"
            dao.title shouldBe "title"
            dao.content shouldBe "content"
            dao.createdAt shouldBe now
            dao.createdBy shouldBe "admin"
            dao.updatedAt shouldBe now
            dao.updatedBy shouldBe "admin"
        }
    }
})
