group = "com.microecommerce.terms.infra"
version = "0.0.1"

val mysqlConnectorVersion: String by project

dependencies {
    implementation(project(":terms:terms-command-application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    runtimeOnly("mysql:mysql-connector-java:$mysqlConnectorVersion")
}

openApiGenerate {
    apiPackage.set("com.microecommerce.terms.openapi.api")
    invokerPackage.set("com.microecommerce.terms.openapi.invoker")
    modelPackage.set("com.microecommerce.terms.openapi.model")
}
