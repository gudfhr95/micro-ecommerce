package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import jakarta.validation.Valid
import org.springframework.stereotype.Service

@Service
class TermsCommandApplicationService(
    private val termsCommandRepository: TermsCommandRepository
) {

    fun createTerms(
        @Valid
        command: CreateTermsCommand
    ): Terms {
        val terms = Terms(
            title = command.title,
            content = command.content
        )

        return termsCommandRepository.save(terms)
    }
}
