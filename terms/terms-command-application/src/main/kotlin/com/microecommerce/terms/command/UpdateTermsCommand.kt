package com.microecommerce.terms.command

import com.microecommerce.terms.vo.TermsId
import jakarta.validation.constraints.NotBlank

data class UpdateTermsCommand(
    val termsId: TermsId,
    @field:NotBlank
    val title: String? = null,
    @field:NotBlank
    val content: String? = null
)
