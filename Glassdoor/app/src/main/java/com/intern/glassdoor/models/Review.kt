package com.intern.glassdoor.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review(
    val advice: String,
    val approvalStatus: String,
    val attributionURL: String,
    val careerOpportunitiesRating: Int,
    val ceoApproval: String,
    val ceoRating: Int,
    val compensationAndBenefitsRating: Int,
    val cons: String,
    val cultureAndValuesRating: Int,
    val currentJob: Boolean,
    val employerId: Int,
    val employerName: String,
    val employmentStatus: String,
    val featuredReview: Boolean,
    val headline: String,
    val helpfulCount: Int,
    val id: Int,
    val jobTitle: String,
    val jobTitleFromDb: String,
    val lengthOfEmployment: String,
    val location: String,
    val newReview: Boolean,
    val notHelpfulCount: Int,
    val overall: String,
    val overallNumeric: Int,
    val pros: String,
    val recommendToFriend: Boolean,
    val reviewDateTime: String,
    val seniorLeadershipRating: Int,
    val sqLogoUrl: String,
    val totalHelpfulCount: Int,
    val workLifeBalanceRating: Int
): Parcelable