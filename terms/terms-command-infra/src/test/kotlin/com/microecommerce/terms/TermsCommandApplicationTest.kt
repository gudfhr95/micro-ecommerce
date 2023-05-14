package com.microecommerce.terms

import com.microecommerce.terms.openapi.model.CreateTermsRequest
import com.microecommerce.terms.openapi.model.UpdateTermsRequest
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
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8").apply {
            withDatabaseName("terms")
        }
    }

    @Autowired
    private lateinit var testClient: WebTestClient

    init {
        val type = "type"
        val createTermsRequest = CreateTermsRequest(
            type = type,
            title = "title",
            content = "content"
        )

        Given("a request to create a new terms") {
            When("the request is valid") {
                Then("it should return 200 with new terms") {
                    postTerms(createTermsRequest)
                        .expectStatus().isOk
                        .expectBody()
                        .jsonPath("$.termsId").isNotEmpty
                        .jsonPath("$.type").isEqualTo(type)
                        .jsonPath("$.title").isEqualTo(createTermsRequest.title)
                        .jsonPath("$.content").isEqualTo(createTermsRequest.content)
                        .jsonPath("$.version").isEqualTo(0)
                }
            }
        }

        Given("a terms and a request to update it") {
            beforeTest {
                postTerms(createTermsRequest)
            }

            val updateTermsRequest = UpdateTermsRequest(
                title = "updated title",
                content = "updated content"
            )

            When("update terms") {
                Then("it should return 200 with updated terms") {
                    testClient.patch()
                        .uri("/terms/$type")
                        .bodyValue(updateTermsRequest)
                        .exchange()
                        .expectStatus().isOk
                        .expectBody()
                        .jsonPath("$.termsId").isNotEmpty
                        .jsonPath("$.type").isEqualTo(type)
                        .jsonPath("$.title").isEqualTo(updateTermsRequest.title)
                        .jsonPath("$.content").isEqualTo(updateTermsRequest.content)
                        .jsonPath("$.version").isEqualTo(1)
                }
            }
        }

        Given("a terms") {
            beforeTest {
                postTerms(createTermsRequest)
            }

            When("delete terms") {
                Then("it should return 204") {
                    testClient.delete()
                        .uri("/terms/$type")
                        .exchange()
                        .expectStatus().isNoContent
                }
            }
        }
    }

    private fun postTerms(request: CreateTermsRequest): WebTestClient.ResponseSpec {
        return testClient.post()
            .uri("/terms")
            .bodyValue(request)
            .exchange()
    }
}
