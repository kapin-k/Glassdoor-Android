package com.intern.glassdoor.models

data class Question(
    val attributionURL: Any,
    val date: Any,
    val employer: String,
    val helpfulCount: Int,
    val id: Int,
    val jobTitle: String,
    val locationCity: Any,
    val locationCountry: Any,
    val locationState: Any,
    val question: String,
    val responses: List<ResponseX>,
    val squareLogo: String,
    val totalHelpfulCount: Int
)