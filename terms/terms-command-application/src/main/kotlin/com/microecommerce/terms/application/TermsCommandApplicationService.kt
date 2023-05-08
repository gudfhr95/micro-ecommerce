package com.microecommerce.terms.application

import com.microecommerce.terms.command.CreateTermsCommand
import com.microecommerce.terms.command.DeleteTermsCommand
import com.microecommerce.terms.command.UpdateTermsCommand
import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.repository.TermsCommandRepository
import com.microecommerce.terms.service.TermsCommandService
import org.springframework.stereotype.Service

@Service
class TermsCommandApplicationService(
    private val termsCommandService: TermsCommandService,
    private val termsCommandRepository: TermsCommandRepository
) {

    fun createTerms(command: CreateTermsCommand): Terms {
        return termsCommandService.createTerms(
            type = command.type,
            title = command.title,
            content = command.content
        )
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
