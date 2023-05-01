import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "com.microecommerce.terms.infra"
version = "0.0.1"

dependencies {
    implementation(project(":terms:terms-command-application"))
}

tasks.named<BootJar>("bootJar") {
    enabled = true
}
