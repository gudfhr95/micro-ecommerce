package com.microecommerce.terms.vo

import com.microecommerce.core.domain.vo.AbstractId
import java.util.*

class TermsId(
    id: String = UUID.randomUUID().toString()
) : AbstractId(id)
