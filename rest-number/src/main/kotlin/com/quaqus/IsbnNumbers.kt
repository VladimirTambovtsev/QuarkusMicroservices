package com.quaqus

import org.eclipse.microprofile.openapi.annotations.media.Schema
import java.time.Instant

@Schema(description = "Several ISBN numbers for books")
data class IsbnNumbers(

    @Schema(required = true)
    val isbn10: String,
    @Schema(required = true)
    val isbn13: String,
    val generationDate: Instant
)
