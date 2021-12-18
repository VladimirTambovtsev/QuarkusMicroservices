package com.quaqus

import javafx.stage.Stage
import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Contact
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Application

@ApplicationPath("/")
@OpenAPIDefinition(
    info = Info(
        title = "Number microservice",
        description = "This is microservice",
        version = "1.0",
        contact = Contact(name = "@google", url = "http://google.com")
    ),
    externalDocs = ExternalDocumentation(url = "http://google.com", description = "All the microservices code"),
    tags = arrayOf(
        Tag(name = "api", description = "Public API in Quarkus"),
        Tag(name = "numbers", description = "Public API in Quarkus")
    )
)
class NumberMicroservice : Application() {

}