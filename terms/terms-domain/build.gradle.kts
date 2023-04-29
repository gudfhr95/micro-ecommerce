group = "com.microecommerce.terms.domain"
version = "0.0.1"

dependencies {
    implementation(project(":core"))
}

tasks.named<Jar>("jar") {
    enabled = true
}
