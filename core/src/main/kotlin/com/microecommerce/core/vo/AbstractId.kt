package com.microecommerce.core.vo

import java.util.*

open class AbstractId {

    var id: String
        private set

    init {
        this.id = UUID.randomUUID().toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (javaClass != other?.javaClass) return false

        return id == (other as AbstractId).id
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
