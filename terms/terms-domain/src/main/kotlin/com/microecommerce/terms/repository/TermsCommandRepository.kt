package com.microecommerce.terms.repository

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.vo.TermsId

interface TermsCommandRepository {

    fun save(terms: Terms): Terms

    fun findByTermsId(termsId: TermsId): Terms?
}
