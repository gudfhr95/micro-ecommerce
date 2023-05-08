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

    var id: Long? = id
        private set

    var version: Long = version ?: 0
        private set

    var isDeleted: Boolean = isDeleted ?: false
        private set

    var createdAt: LocalDateTime = createdAt ?: LocalDateTime.now()
        private set

    var createdBy: String = createdBy ?: "system"
        private set

    var updatedAt: LocalDateTime = updatedAt ?: LocalDateTime.now()
        private set

    var updatedBy: String = updatedBy ?: "system"
        private set

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
