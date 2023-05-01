package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.DeleteTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
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

    fun updateTerms(
        @Valid
        command: UpdateTermsCommand
    ): Terms {
        val terms = termsCommandRepository.findByTermsId(command.termsId)
            ?: throw IllegalArgumentException("Terms not found")

        terms.update(
            title = command.title,
            content = command.content
        )

        return termsCommandRepository.save(terms)
    }

    fun deleteTerms(
        command: DeleteTermsCommand
    ) {
        termsCommandRepository.deleteByTermsId(command.termsId)
    }
}
