package com.microecommerce.core.output.repository.dao

import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
class AbstractDao(
    id: Long? = null,
    version: Long? = null,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = id
        protected set

    @Version
    var version: Long = version ?: 0L

    var isDeleted: Boolean = false
        protected set

    @CreatedDate
    var createdAt: LocalDateTime = createdAt ?: LocalDateTime.now()
        protected set

    var createdBy: String = createdBy ?: "system"
        protected set

    @LastModifiedDate
    var updatedAt: LocalDateTime = updatedAt ?: LocalDateTime.now()
        protected set

    var updatedBy: String = updatedBy ?: "system"
        protected set

    fun delete() {
        this.isDeleted = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractDao

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}
