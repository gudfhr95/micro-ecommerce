package com.microecommerce.terms.service

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import org.springframework.stereotype.Service

@Service
class TermsCommandService(
    private val termsCommandRepository: TermsCommandRepository
) {

    fun createTerms(
        type: String,
        title: String,
        content: String
    ): Terms {
        check(termsCommandRepository.existsByType(type).not()) { "Terms already exists" }

        val terms = Terms(
            type = type,
            title = title,
            content = content
        )

        return termsCommandRepository.save(terms)
    }
}
