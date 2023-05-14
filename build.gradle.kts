import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
    id("io.gitlab.arturbosch.detekt")
    id("jacoco")

    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
    kotlin("plugin.spring")
    kotlin("plugin.jpa") apply false
    id("org.openapi.generator")
}

group = "com.microecommerce"
version = "0.0.1"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring")

    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }

    ktlint {
        filter {
            exclude { entry ->
                entry.file.toString().contains("generated")
            }
        }
    }

    detekt {
        config.setFrom(files("$rootDir/detekt.yml"))
        buildUponDefaultConfig = true
        allRules = false
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.named<BootJar>("bootJar") {
        enabled = false
    }

    tasks.named<Jar>("jar") {
        enabled = false
    }
}

subprojects {
    val junitVersion: String by project
    val mockkVersion: String by project

    apply(plugin = "jacoco")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-starter")

        testImplementation("io.kotest:kotest-runner-junit5:$junitVersion")
        testImplementation("io.mockk:mockk:$mockkVersion")
    }

    if (project.name.endsWith("core") || project.name.endsWith("domain")) {
        apply(plugin = "kotlin-jpa")

        allOpen {
            annotation("jakarta.persistence.Entity")
            annotation("jakarta.persistence.Embeddable")
            annotation("jakarta.persistence.MappedSuperclass")
        }

        tasks.named<Jar>("jar") {
            enabled = true
        }
    }

    if (project.name.endsWith("application")) {
        tasks.named<Jar>("jar") {
            enabled = true
        }
    }

    if (project.name.endsWith("infra")) {
        val springdocOpenapiVersion: String by project
        val testContainersVersion: String by project
        val kotestExtensionsSpringVersion: String by project

        apply(plugin = "kotlin-jpa")
        apply(plugin = "org.openapi.generator")

        dependencyManagement {
            imports {
                mavenBom("org.testcontainers:testcontainers-bom:$testContainersVersion")
            }
        }

        sourceSets {
            main {
                kotlin {
                    srcDir("$buildDir/generated/openapi/src/main/kotlin")
                }
            }
        }

        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-validation")
            implementation("org.springframework.boot:spring-boot-starter-web")
            implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocOpenapiVersion")

            testImplementation("org.springframework.boot:spring-boot-starter-webflux")
            testImplementation("org.springframework.boot:spring-boot-starter-test")
            testImplementation("io.kotest.extensions:kotest-extensions-spring:$kotestExtensionsSpringVersion")
            testImplementation("org.testcontainers:junit-jupiter")
            testImplementation("org.testcontainers:mysql")
        }

        openApiGenerate {
            generatorName.set("kotlin-spring")
            inputSpec.set("$projectDir/openapi.yaml")
            outputDir.set("$buildDir/generated/openapi")
            configOptions.putAll(
                mapOf(
                    "useSpringBoot3" to "true",
                    "useTags" to "true",
                    "interfaceOnly" to "true"
                )
            )
        }

        tasks.runKtlintCheckOverMainSourceSet {
            mustRunAfter(tasks.openApiGenerate)
        }

        tasks.withType<KotlinCompile> {
            dependsOn(tasks.openApiGenerate)
        }

        tasks.named<BootJar>("bootJar") {
            enabled = true
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        finalizedBy("jacocoTestReport")
    }

    tasks.jacocoTestReport {
        reports {
            html.required.set(true)
            xml.required.set(false)
            csv.required.set(false)
        }

        finalizedBy("jacocoTestCoverageVerification")
    }

    tasks.jacocoTestCoverageVerification {
        violationRules {
            rule {
                element = "CLASS"

                limit {
                    counter = "BRANCH"
                    value = "COVEREDRATIO"
                    minimum = "0.90".toBigDecimal()
                }

                limit {
                    counter = "LINE"
                    value = "COVEREDRATIO"
                    minimum = "0.80".toBigDecimal()
                }

                excludes = listOf(
                    "com.microecommerce.core.*",
                    "*.openapi.*"
                )
            }
        }
    }
}
