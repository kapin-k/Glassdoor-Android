package com.intern.glassdoor.models

data class BasePay(
    val amount: Int,
    val currencyCode: String,
    val name: String,
    val symbol: String
)