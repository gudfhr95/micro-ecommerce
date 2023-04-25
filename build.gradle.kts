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
java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "jacoco")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin-spring")

    repositories {
        mavenCentral()
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
            }
        }
    }
}
