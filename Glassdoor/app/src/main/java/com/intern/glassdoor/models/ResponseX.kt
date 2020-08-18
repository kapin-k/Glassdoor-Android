package com.intern.glassdoor.models

data class ResponseX(
    val approvalStatus: String,
    val createdDate: String,
    val empRep: Boolean,
    val helpfulCount: Int,
    val id: Int,
    val notHelpfulCount: Int,
    val responseText: String,
    val userHandle: String
)