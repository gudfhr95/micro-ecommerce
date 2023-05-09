package com.microecommerce.terms.output.repository

import com.microecommerce.terms.output.repository.dao.TermsDao
import org.springframework.data.jpa.repository.JpaRepository

interface TermsCommandJpaRepository : JpaRepository<TermsDao, Long> {

    fun findByTypeAndIsDeletedFalse(type: String): TermsDao?

    fun existsByTypeAndIsDeletedFalse(type: String): Boolean
}
