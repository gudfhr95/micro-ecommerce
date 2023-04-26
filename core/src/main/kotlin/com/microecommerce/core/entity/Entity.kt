package com.microecommerce.core.entity

import java.time.LocalDateTime

open class Entity(
    protected val id: Long? = null,
    protected val createdAt: LocalDateTime? = null,
    protected val createdBy: String? = null,
    protected val updatedAt: LocalDateTime? = null,
    protected val updatedBy: String? = null
)
