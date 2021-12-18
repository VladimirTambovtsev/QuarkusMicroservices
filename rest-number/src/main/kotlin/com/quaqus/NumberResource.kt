package com.quaqus

import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.jboss.logging.Logger
import java.time.Instant
import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import kotlin.random.Random

@Path("/api/numbers")
@Tag(name = "Number REST Endpoint")
class NumberResource {

    @Inject
    private lateinit var logger: Logger

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
        summary = "Generates books ISBN",
        description = "ABCDEF"
    )
    fun generateIsbnNumbers(): IsbnNumbers {
        val isbnNumbers = IsbnNumbers(
            isbn10 = "10-" + Random.nextInt(100_000),
            isbn13 = "13-" + Random.nextInt(100_000_000),
            generationDate = Instant.now()
        )
        logger.info("Numbers generated $isbnNumbers")
        return isbnNumbers
    }
}