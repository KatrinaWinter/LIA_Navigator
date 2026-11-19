package com.example.lianavigator.viewmodel

import com.example.lianavigator.data.Company
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.lianavigator.ui.CompanyUiState

class CompanyViewModel {
    val testCompany = listOf(
        Company(
            id = 1,
            name = "Spotify",
            category = "Teknik",
            isSaved = false
        )
    )
    private val _uiState = mutableStateOf(
        CompanyUiState(
            companies = testCompany
        )
    )

    val uiState: State<CompanyUiState> = _uiState

    fun addCompany(name: String, category: String) {
        val newId = (_uiState.value.companies.maxOfOrNull { it.id } ?: 0) + 1

        val newCompany = Company(
            id = newId,
            name = name,
            category = category
        )

        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies + newCompany
        )
    }
}

