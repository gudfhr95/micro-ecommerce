package com.microecommerce.terms.output.repository

import com.microecommerce.terms.output.repository.dao.TermsDao
import org.springframework.data.repository.CrudRepository

interface TermsCommandJdbcRepository : CrudRepository<TermsDao, Long>
