package com.microecommerce.terms.repository

import com.microecommerce.terms.entity.Terms

interface TermsCommandRepository {

    fun save(terms: Terms): Terms

    fun findByType(type: String): Terms?

    fun existsByType(type: String): Boolean

    fun deleteByType(type: String)
}
