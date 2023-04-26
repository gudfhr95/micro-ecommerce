import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "com.microecommerce.terms.core"
version = "0.0.1"

tasks.named<BootJar>("bootJar") {
    enabled = false
}

tasks.named<Jar>("jar") {
    enabled = true
}
