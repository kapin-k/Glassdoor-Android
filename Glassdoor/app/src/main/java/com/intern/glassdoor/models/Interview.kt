package com.intern.glassdoor.models

data class Interview(
    val approvalStatus: String,
    val attributionURL: String,
    val employerId: Int,
    val employerName: String,
    val featuredReview: Boolean,
    val helpfulCount: Int,
    val id: Int,
    val interviewDate: Any,
    val interviewSource: String,
    val interviewSteps: Any,
    val jobTitle: String,
    val location: String,
    val negotiationDetails: Any,
    val newReview: Boolean,
    val notHelpfulCount: Int,
    val otherSteps: Any,
    val outcome: String,
    val processAnswer: String,
    val processDifficulty: String,
    val processInterviewExperience: Any,
    val processLength: Int,
    val processOverallExperience: String,
    val questions: List<Question>,
    val reasonForDeclining: Any,
    val reviewDateTime: String,
    val sqLogoUrl: String,
    val testSteps: Any,
    val totalHelpfulCount: Int
)