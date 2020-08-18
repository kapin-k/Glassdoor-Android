package com.intern.glassdoor.models

data class Response(
    val jessionid: String,
    val results: Array<Result>? = null,
    val status: String,
    val success: Boolean
)