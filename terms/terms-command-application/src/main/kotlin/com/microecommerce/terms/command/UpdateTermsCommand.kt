package com.microecommerce.terms.command

data class UpdateTermsCommand(
    val type: String,
    val title: String? = null,
    val content: String? = null
)
