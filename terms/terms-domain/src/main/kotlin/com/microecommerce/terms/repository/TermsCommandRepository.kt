package com.microecommerce.terms.repository

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.vo.TermsId

interface TermsCommandRepository {

    fun save(terms: Terms): Terms

    fun findByType(type: String): Terms?

    fun deleteByTermsId(termsId: TermsId)
}
