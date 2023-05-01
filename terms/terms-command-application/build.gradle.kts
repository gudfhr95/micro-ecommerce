group = "com.microecommerce.terms.application"
version = "0.0.1"

dependencies {
    implementation(project(":terms:terms-domain"))
}

tasks.named<Jar>("jar") {
    enabled = true
}
