import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.6" apply false
    id("io.spring.dependency-management") version "1.1.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
    id("io.gitlab.arturbosch.detekt").version("1.23.0-RC2")
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20" apply false
}

group = "com.microecommerce"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "io.gitlab.arturbosch.detekt")

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
    }

    detekt {
        config = files("$rootDir/detekt.yml")
        buildUponDefaultConfig = true
        allRules = false
    }
}
