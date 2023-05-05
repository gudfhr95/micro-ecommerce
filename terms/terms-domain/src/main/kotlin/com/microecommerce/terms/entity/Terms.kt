package com.microecommerce.terms.entity

import com.microecommerce.core.entity.AbstractEntity
import com.microecommerce.terms.vo.TermsId
import java.time.LocalDateTime

class Terms(
    id: Long? = null,
    version: Long? = null,
    val termsId: TermsId = TermsId(),
    title: String,
    content: String,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) : AbstractEntity(id, version, createdAt, createdBy, updatedAt, updatedBy) {

    var title: String = title
        private set(value) {
            require(value.isNotBlank()) { "Title cannot be blank" }

            field = value
        }
    var content: String = content
        private set(value) {
            require(value.isNotBlank()) { "Content cannot be blank" }

            field = value
        }

    init {
        this.title = title
        this.content = content
    }

    fun update(
        title: String? = null,
        content: String? = null
    ) {
        title?.let { this.title = it }
        content?.let { this.content = content }
    }
}
