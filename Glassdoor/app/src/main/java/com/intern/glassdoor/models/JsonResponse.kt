package com.intern.glassdoor.models

data class JsonResponse(
    val jessionid: String,
    val response: Response,
    val status: String,
    val success: Boolean
)