rootProject.name = "micro-ecommerce"

pluginManagement {
    val kotlinVersion: String by settings
    val ktlintVersion: String by settings
    val detektVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val openapiGeneratorVersion: String by settings

    plugins {
        kotlin("jvm") version kotlinVersion
        id("org.jlleitschuh.gradle.ktlint") version ktlintVersion
        id("io.gitlab.arturbosch.detekt") version detektVersion

        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        id("org.openapi.generator") version openapiGeneratorVersion
    }
}

include("core")

include("terms:terms-domain")
include("terms:terms-command-application")
include("terms:terms-command-infra")
