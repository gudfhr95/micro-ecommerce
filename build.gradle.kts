import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC2")
    id("jacoco")

    id("org.springframework.boot") version "3.0.6" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    kotlin("plugin.spring") version "1.8.20" apply false
}

group = "com.microecommerce"
version = "0.0.1"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "jacoco")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.springframework.boot:spring-boot-starter")

        testImplementation("io.kotest:kotest-runner-junit5:5.6.1")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
        testImplementation("io.mockk:mockk:1.13.5")
        testImplementation("com.ninja-squad:springmockk:4.0.2")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        finalizedBy("jacocoTestReport")
    }

    detekt {
        config = files("$rootDir/detekt.yml")
        buildUponDefaultConfig = true
        allRules = false
    }

    tasks.jacocoTestReport {
        reports {
            html.isEnabled = true
            xml.isEnabled = false
            csv.isEnabled = false
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
                    "com.microecommerce.core.*"
                )
            }
        }
    }
}
