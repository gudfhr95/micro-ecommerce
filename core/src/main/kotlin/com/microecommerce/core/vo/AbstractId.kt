package com.microecommerce.core.vo

import java.util.*

open class AbstractId {

    val id: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false

        return id == (other as AbstractId).id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
