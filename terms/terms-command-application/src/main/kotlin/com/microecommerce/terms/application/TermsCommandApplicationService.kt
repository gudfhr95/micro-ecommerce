package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.DeleteTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import org.springframework.stereotype.Service

@Service
class TermsCommandApplicationService(
    private val termsCommandRepository: TermsCommandRepository
) {

    fun createTerms(command: CreateTermsCommand): Terms {
        val terms = Terms(
            type = command.type,
            title = command.title,
            content = command.content
        )

        return termsCommandRepository.save(terms)
    }

    fun updateTerms(command: UpdateTermsCommand): Terms {
        val terms = termsCommandRepository.findByType(command.type)
            ?: throw IllegalArgumentException("Terms not found")

        terms.update(
            title = command.title,
            content = command.content
        )

        return termsCommandRepository.save(terms)
    }

    fun deleteTerms(command: DeleteTermsCommand) {
        termsCommandRepository.deleteByType(command.type)
    }
}
