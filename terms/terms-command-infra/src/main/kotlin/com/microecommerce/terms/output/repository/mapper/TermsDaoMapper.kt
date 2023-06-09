package com.microecommerce.terms.output.repository.mapper

import com.microecommerce.terms.entity.Terms
import com.microecommerce.terms.output.repository.dao.TermsDao
import com.microecommerce.terms.vo.TermsId

object TermsDaoMapper {

    fun toEntity(termsDao: TermsDao) = Terms(
        id = termsDao.id,
        termsId = TermsId(termsDao.termsId),
        type = termsDao.type,
        title = termsDao.title,
        content = termsDao.content,
        version = termsDao.version,
        createdAt = termsDao.createdAt,
        createdBy = termsDao.createdBy,
        updatedAt = termsDao.updatedAt,
        updatedBy = termsDao.updatedBy
    )

    fun fromEntity(terms: Terms) = TermsDao(
        id = terms.id,
        termsId = terms.termsId.id,
        type = terms.type,
        title = terms.title,
        content = terms.content,
        version = terms.version,
        createdAt = terms.createdAt,
        createdBy = terms.createdBy,
        updatedAt = terms.updatedAt,
        updatedBy = terms.updatedBy
    )
}
