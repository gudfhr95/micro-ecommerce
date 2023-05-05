import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.8.21"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC3")
    id("jacoco")

    id("org.springframework.boot") version "3.0.6" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    kotlin("plugin.spring") version "1.8.21" apply false
    id("org.openapi.generator") version "6.5.0"
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
    apply(plugin = "jacoco")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-starter")

        testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
        testImplementation("io.mockk:mockk:1.13.5")
    }

    if (project.name.endsWith("core") || project.name.endsWith("domain")) {
        tasks.named<Jar>("jar") {
            enabled = true
        }
    }

    if (project.name.endsWith("application")) {
        dependencies {
            implementation("org.springframework.boot:spring-boot-starter-validation")
        }

        tasks.named<Jar>("jar") {
            enabled = true
        }
    }

    if (project.name.endsWith("infra")) {
        apply(plugin = "org.openapi.generator")

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
            implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
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
