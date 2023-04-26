import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "com.microecommerce.terms.domain"
version = "0.0.1"

dependencies {
    implementation(project(":core"))
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}
