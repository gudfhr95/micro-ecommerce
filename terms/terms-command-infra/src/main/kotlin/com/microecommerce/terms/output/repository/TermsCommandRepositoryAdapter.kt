package com.microecommerce.terms.output.repository

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.mapper.TermsDaoMapper
import com.microecommerce.terms.repository.TermsCommandRepository
import com.microecommerce.terms.vo.TermsId
import org.springframework.stereotype.Repository

@Repository
class TermsCommandRepositoryAdapter(
    private val termsCommandJdbcRepository: TermsCommandJdbcRepository
) : TermsCommandRepository {

    override fun save(terms: Terms): Terms {
        val termsDao = termsCommandJdbcRepository.save(
            TermsDaoMapper.fromEntity(terms)
        )

        return TermsDaoMapper.toEntity(termsDao)
    }

    override fun findByTermsId(termsId: TermsId): Terms? {
        TODO("Not yet implemented")
    }

    override fun deleteByTermsId(termsId: TermsId) {
        TODO("Not yet implemented")
    }
}
