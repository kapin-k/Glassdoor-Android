package com.intern.glassdoor.models

data class Result(
    val interview: Interview?,
    val relevancy: Relevancy?,
    val review: Review?,
    val salary: Salary?,
    val source: String,
    val suggestedPost: Boolean,
    val type: String
)