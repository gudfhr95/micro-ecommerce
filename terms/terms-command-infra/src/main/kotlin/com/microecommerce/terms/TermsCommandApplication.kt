package com.microecommerce.terms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class TermsCommandApplication

fun main(args: Array<String>) {
    runApplication<TermsCommandApplication>(args = args)
}
