package com.example.lianavigator.ui

import com.example.lianavigator.data.Company

// Represents the UI state containing the list of companies.
data class CompanyUiState(
    // Stores the companies currently displayed in the app.
    val companies: List<Company> = emptyList()
)