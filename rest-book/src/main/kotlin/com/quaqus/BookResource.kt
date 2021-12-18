package com.quaqus

import org.eclipse.microprofile.faulttolerance.Fallback
import org.eclipse.microprofile.faulttolerance.Retry
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.eclipse.microprofile.rest.client.inject.RestClient
import org.jboss.logging.Logger
import java.io.File
import java.io.FileNotFoundException
import java.io.PrintWriter
import java.lang.Exception
import java.time.Instant
import javax.inject.Inject
import javax.json.bind.JsonbBuilder
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/api/books")
@Tag(name = "Book REST Endpoint")
class BookResource {

    @Inject
    @RestClient
    lateinit var numberProxy: NumberProxy

    @Inject
    lateinit var logger: Logger

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a book", description = "Creates a books with ISBN number")
    @Retry(maxRetries = 1, delay = 3000)
    @Fallback(fallbackMethod = "fallingOnCreateABook")
    fun createABook(
        @FormParam("title")
        title: String,
        @FormParam("author")
        author: String,
        @FormParam("yearOfPublication")
        yearOfPublication: Int,
        @FormParam("genre")
        genre: String
    ): Response {
        val book = Book(
            title = title,
            author = author,
            yearOfPublication = yearOfPublication,
            genre = genre,
            creationDate = Instant.now(),
            isbn13 = ""
        )
        val test = numberProxy.generateIsbnNumbers()?.isbn13
        println(test)
        logger.info("$book was created")
        return Response.status(201).entity(book).build()
    }

    fun fallingOnCreateABook(
        title: String,
        author: String,
        yearOfPublication: Int,
        genre: String
    ): Response {
        val book = Book(
            title = title,
            author = author,
            yearOfPublication = yearOfPublication,
            genre = genre,
            creationDate = Instant.now(),
            isbn13 = "Will be set later"
        )
        logger.info("$book was created")
        try {
            saveOnDisk(book)
        } catch (e: FileNotFoundException) {
            throw e
        }
        return Response.status(206).entity(book).build()
    }

    private fun saveOnDisk(book: Book) {
        val bookJson = JsonbBuilder.create().toJson(book)
        try {
            val file = File("bookV-${Instant.now().toEpochMilli()}.json")
            file.writeText(bookJson)
        } catch (e: FileNotFoundException) {
            throw e
        }
    }
}