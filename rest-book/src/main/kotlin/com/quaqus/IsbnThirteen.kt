package com.quaqus

import javax.json.bind.annotation.JsonbProperty

class IsbnThirteen (
    @JsonbProperty("isbn_13")
    val isbn13: String
)
