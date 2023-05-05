group = "com.microecommerce.terms.infra"
version = "0.0.1"

dependencies {
    implementation(project(":terms:terms-command-application"))
}

openApiGenerate {
    apiPackage.set("com.microecommerce.terms.openapi.api")
    invokerPackage.set("com.microecommerce.terms.openapi.invoker")
    modelPackage.set("com.microecommerce.terms.openapi.model")
}
