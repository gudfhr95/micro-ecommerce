package com.microecommerce.terms.output.repository

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.mapper.TermsDaoMapper
import com.microecommerce.terms.repository.TermsCommandRepository
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

    override fun findByType(type: String): Terms? {
        val termsDao = termsCommandJdbcRepository.findByType(type)

        return termsDao?.let { TermsDaoMapper.toEntity(it) }
    }

    override fun deleteByType(type: String) {
        val termsDao = termsCommandJdbcRepository.findByType(type)

        termsDao?.run {
            this.delete()
            termsCommandJdbcRepository.save(this)
        }
    }
}
