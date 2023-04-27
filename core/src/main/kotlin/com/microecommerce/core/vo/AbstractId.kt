package com.microecommerce.core.vo

import java.util.*

open class AbstractId(
    val id: String = UUID.randomUUID().toString()
) {

    init {
        require(id.isNotBlank()) { "Id cannot be blank" }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AbstractId

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
