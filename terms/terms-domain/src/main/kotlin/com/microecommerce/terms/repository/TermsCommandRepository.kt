package com.microecommerce.terms.repository

import com.microecommerce.terms.entity.Terms

interface TermsCommandRepository {

    fun save(terms: Terms): Terms
}
