package com.quaqus

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant
import javax.json.bind.annotation.JsonbDateFormat
import javax.json.bind.annotation.JsonbProperty

@Schema(description = "A book")
data class Book (
    @Schema(required = true)
    val title: String,

    val author: String,

    @JsonbProperty("year_of_publication")
    val yearOfPublication: Int,

    val genre: String,

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    val isbn13: String,

    @JsonbDateFormat("yyyy-MM-dd")
    @JsonbProperty("creation_date")
    @Schema(implementation = String::class, format = "date")
    val creationDate: Instant
)