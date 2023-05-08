package com.microecommerce.terms.repository

import com.microecommerce.terms.entity.Terms

interface TermsCommandRepository {

    fun save(terms: Terms): Terms

    fun findByType(type: String): Terms?

    fun deleteByType(type: String)
}
