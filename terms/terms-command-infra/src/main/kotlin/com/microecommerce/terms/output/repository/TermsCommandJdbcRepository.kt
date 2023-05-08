package com.microecommerce.terms.output.repository

import com.microecommerce.terms.output.repository.dao.TermsDao
import org.springframework.data.repository.CrudRepository

interface TermsCommandJdbcRepository : CrudRepository<TermsDao, Long> {

    fun findByType(type: String): TermsDao?

    fun existsByType(type: String): Boolean
}
