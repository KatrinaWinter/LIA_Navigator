package com.example.lianavigator.data

// Represents a company saved by the user, including its details and current status.
data class Company(
    val id: Int, // Unique identifier for each company.
    val name: String, // The name of the company.
    val category: String, // The company's industry or category.
    val websiteUrl: String? = null, // Optional website address.
    val isSaved: Boolean = false, // Indicates whether the company is saved.
    val status: CompanyStatus = CompanyStatus.SAVED // The company's current LIA status.
)

// Defines the different statuses a company can have during the LIA search process.
enum class CompanyStatus {
    SAVED, // Company has been saved for later.
    CONTACT_ONGOING, // The user is in contact with the company.
    APPLICATION_SENT, // An application has been submitted.
    LIA_CONFIRMED // The LIA placement has been confirmed.
}