package com.microecommerce.core.entity

import java.time.LocalDateTime

open class IdentifiedDomainObject(
    protected val id: Long? = null,
    protected val createdAt: LocalDateTime? = null,
    protected val createdBy: String? = null,
    protected val updatedAt: LocalDateTime? = null,
    protected val updatedBy: String? = null
)
