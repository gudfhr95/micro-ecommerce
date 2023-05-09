package com.microecommerce.terms.output.repository

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.mapper.TermsDaoMapper
import com.microecommerce.terms.repository.TermsCommandRepository
import org.springframework.stereotype.Repository

@Repository
class TermsCommandRepositoryAdapter(
    private val termsCommandJpaRepository: TermsCommandJpaRepository
) : TermsCommandRepository {

    override fun save(terms: Terms): Terms {
        val termsDao = termsCommandJpaRepository.save(
            TermsDaoMapper.fromEntity(terms)
        )

        return TermsDaoMapper.toEntity(termsDao)
    }

    override fun findByType(type: String): Terms? {
        val termsDao = termsCommandJpaRepository.findByType(type)

        return termsDao?.let { TermsDaoMapper.toEntity(it) }
    }

    override fun existsByType(type: String): Boolean {
        return termsCommandJpaRepository.existsByType(type)
    }

    override fun deleteByType(type: String) {
        val termsDao = termsCommandJpaRepository.findByType(type)

        termsDao?.run {
            this.delete()
            termsCommandJpaRepository.save(this)
        }
    }
}
