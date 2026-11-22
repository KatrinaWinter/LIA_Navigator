package com.example.lianavigator.data

data class Company(
    val id: Int,
    val name: String,
    val category: String,
    val websiteUrl: String? = null,
    val isSaved: Boolean = false,
    val status: CompanyStatus = CompanyStatus.SAVED
)


enum class CompanyStatus {
    SAVED,
    CONTACT_ONGOING,
    APPLICATION_SENT,
    LIA_CONFIRMED
}