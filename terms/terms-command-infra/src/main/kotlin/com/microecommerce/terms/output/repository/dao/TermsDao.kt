package com.microecommerce.terms.output.repository.dao

import com.microecommerce.core.dao.AbstractJdbcDao
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table("terms")
class TermsDao(
    id: Long? = null,
    version: Long?,
    val termsId: String,
    val type: String,
    val title: String,
    val content: String,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) : AbstractJdbcDao(id, version, createdAt, createdBy, updatedAt, updatedBy)
