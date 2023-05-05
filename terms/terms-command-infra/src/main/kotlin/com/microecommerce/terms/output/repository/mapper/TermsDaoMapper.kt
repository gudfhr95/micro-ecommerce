package com.microecommerce.terms.output.repository.mapper

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.dao.TermsDao
import com.microecommerce.terms.vo.TermsId

object TermsDaoMapper {

    fun toEntity(termsDao: TermsDao) = Terms(
        id = termsDao.id,
        version = termsDao.version,
        termsId = TermsId(termsDao.termsId),
        title = termsDao.title,
        content = termsDao.content,
        createdAt = termsDao.createdAt,
        createdBy = termsDao.createdBy,
        updatedAt = termsDao.updatedAt,
        updatedBy = termsDao.updatedBy
    )

    fun fromEntity(terms: Terms) = TermsDao(
        id = terms.id,
        version = terms.version,
        termsId = terms.termsId.id,
        title = terms.title,
        content = terms.content,
        createdAt = terms.createdAt,
        createdBy = terms.createdBy,
        updatedAt = terms.updatedAt,
        updatedBy = terms.updatedBy
    )
}
