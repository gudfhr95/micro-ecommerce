package com.microecommerce.terms.command

import com.microecommerce.terms.vo.TermsId

data class DeleteTermsCommand(
    val termsId: TermsId
)
