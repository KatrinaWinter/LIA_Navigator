package com.example.lianavigator.ui

import com.example.lianavigator.data.Company

data class CompanyUiState(
    val companies: List<Company> = emptyList()
)