package com.microecommerce.terms.command

data class CreateTermsCommand(
    val type: String,
    val title: String,
    val content: String
)
