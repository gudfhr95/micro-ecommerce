package com.microecommerce.core.output.repository.dao

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.annotation.Version
import java.time.LocalDateTime

open class AbstractJdbcDao(
    id: Long? = null,
    version: Long? = null,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) {

    @Id
    var id: Long? = id
        private set

    @Version
    var version: Long = version ?: 0
        private set

    var isDeleted: Boolean = false
        private set

    @CreatedDate
    var createdAt: LocalDateTime = createdAt ?: LocalDateTime.now()
        private set

    var createdBy: String = createdBy ?: "system"
        private set

    @LastModifiedDate
    var updatedAt: LocalDateTime = updatedAt ?: LocalDateTime.now()
        private set

    var updatedBy: String = updatedBy ?: "system"
        private set

    fun delete() {
        this.isDeleted = true
    }
}
