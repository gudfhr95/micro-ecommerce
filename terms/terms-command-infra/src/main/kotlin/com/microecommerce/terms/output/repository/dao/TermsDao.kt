package com.microecommerce.terms.output.repository.dao

import com.microecommerce.core.output.repository.dao.AbstractDao
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "terms")
@Entity
class TermsDao(
    id: Long? = null,
    @Column
    val termsId: String,
    @Column
    val type: String,
    @Column
    val title: String,
    @Column
    val content: String,
    version: Long? = null,
    createdAt: LocalDateTime? = null,
    createdBy: String? = null,
    updatedAt: LocalDateTime? = null,
    updatedBy: String? = null
) : AbstractDao(id, version, createdAt, createdBy, updatedAt, updatedBy)
