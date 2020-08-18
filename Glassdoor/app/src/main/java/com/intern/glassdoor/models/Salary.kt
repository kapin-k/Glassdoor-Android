package com.intern.glassdoor.models

data class Salary(
    val attributionURL: String,
    val basePay: BasePay,
    val employerId: Int,
    val employerName: String,
    val employmentStatus: String,
    val id: Int,
    val jobTitle: String,
    val location: String,
    val meanBasePay: MeanBasePay,
    val payDeltaLocationType: String,
    val payDeltaPercent: Double,
    val payPeriod: String,
    val reviewDateTime: String,
    val sqLogoUrl: String
)