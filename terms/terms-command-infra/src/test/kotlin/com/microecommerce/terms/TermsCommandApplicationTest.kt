package com.microecommerce.terms

import com.microecommerce.terms.openapi.model.CreateTermsRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class TermsCommandApplicationTest : BehaviorSpec() {

    override fun extensions() = listOf(SpringExtension)

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.33").apply {
            withDatabaseName("terms")
        }
    }

    @Autowired
    private lateinit var testClient: WebTestClient

    init {
        Given("a request to create a new terms") {
            val request = CreateTermsRequest(
                type = "type",
                title = "title",
                content = "content"
            )

            When("the request is valid") {
                Then("should return 200 with valid response") {
                    testClient.post()
                        .uri("/terms")
                        .bodyValue(request)
                        .exchange()
                        .expectStatus().isOk
                }
            }
        }
    }
}
