group = "com.microecommerce.terms.infra"
version = "0.0.1"

dependencies {
    implementation(project(":terms:terms-command-application"))
}

openApiGenerate {
    apiPackage.set("com.microecommerce.terms.input.rest")
    invokerPackage.set("com.microecommerce.terms.input.rest.invoker")
    modelPackage.set("com.microecommerce.terms.input.rest.model")
}
