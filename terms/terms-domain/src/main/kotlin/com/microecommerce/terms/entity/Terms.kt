package com.microecommerce.terms.entity

import com.microecommerce.core.entity.Entity
import com.microecommerce.terms.vo.TermsId

class Terms : Entity {

    val termsId = TermsId()
    var title: String
        private set(value) {
            require(value.isNotBlank()) { "Title cannot be blank" }

            field = value
        }
    var content: String
        private set(value) {
            require(value.isNotBlank()) { "Content cannot be blank" }

            field = value
        }

    constructor(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
