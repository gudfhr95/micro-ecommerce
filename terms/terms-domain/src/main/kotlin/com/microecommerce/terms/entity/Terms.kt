package com.microecommerce.terms.entity

import com.microecommerce.core.entity.AbstractEntity
import com.microecommerce.terms.vo.TermsId
import java.time.LocalDateTime

class Terms(
    id: Long? = null,
    version: Long? = null,
    val termsId: TermsId = TermsId(),
    type: String,
    title: String,
    content: String,
    isDeleted: Boolean? = null,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) : AbstractEntity(id, version, isDeleted, createdAt, createdBy, updatedAt, updatedBy) {

    var type: String = type
        private set(value) {
            require(value.isNotBlank()) { "Type cannot be blank" }

            field = value
        }

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
        this.type = type
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
