package com.microecommerce.terms.command

import jakarta.validation.constraints.NotBlank

data class CreateTermsCommand(
    @field:NotBlank
    val title: String,
    @field:NotBlank
    val content: String
)
