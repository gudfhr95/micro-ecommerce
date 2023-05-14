package com.microecommerce.core.domain.entity

import java.time.LocalDateTime

open class AbstractEntity(
    id: Long? = null,
    version: Long? = null,
    isDeleted: Boolean? = null,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) {

    val id: Long? = id

    val version: Long = version ?: 0

    val isDeleted: Boolean = isDeleted ?: false

    val createdAt: LocalDateTime = createdAt ?: LocalDateTime.now()

    val createdBy: String = createdBy ?: "system"

    val updatedAt: LocalDateTime = updatedAt ?: LocalDateTime.now()

    val updatedBy: String = updatedBy ?: "system"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
