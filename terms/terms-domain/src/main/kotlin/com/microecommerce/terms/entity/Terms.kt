package com.microecommerce.terms.entity

import com.microecommerce.core.entity.Entity
import com.microecommerce.terms.vo.TermsId

class Terms(
    val termsId: TermsId,
    val title: String,
    val content: String
) : Entity()
