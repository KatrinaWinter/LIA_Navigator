package com.example.lianavigator.viewmodel

import com.example.lianavigator.data.Company
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lianavigator.data.CompanyStatus
import com.example.lianavigator.ui.CompanyUiState

// Manages company data and handles operations such as adding, updating and deleting companies.
class CompanyViewModel : ViewModel() {

    // Provides sample companies used to populate the app during development.
    val testCompany = listOf(
        Company(
            id = 1,
            name = "Spotify",
            category = "Teknik",
            isSaved = false,
            websiteUrl = "https://www.spotify.com"
        ),
        Company(
            id = 2,
            name = "Volvo",
            category = "Teknik",
            isSaved = false,
            websiteUrl = "https://www.volvo.com",
            status = CompanyStatus.CONTACT_ONGOING
        ),
        Company(
            id = 3,
            name = "Epassi",
            category = "Ux/Ui",
            isSaved = false,
            websiteUrl = "https://www.epassi.com"
        )
    )

    // Holds the internal UI state, including the current list of companies.
    private val _uiState = mutableStateOf(
        CompanyUiState(
            companies = testCompany
        )
    )

    // Exposes the UI state as read-only data for the UI to observe.
    val uiState: State<CompanyUiState> = _uiState

    // Adds a new company to the list and assigns it a unique ID.
    fun addCompany(name: String, category: String, websiteUrl: String? = null) {

        // Finds the highest existing ID and generates the next ID.
        val newId = (_uiState.value.companies.maxOfOrNull { it.id } ?: 0) + 1

        // Creates a new company using the provided information.
        val newCompany = Company(
            id = newId,
            name = name,
            category = category,
            websiteUrl = websiteUrl
        )

        // Updates the UI state with the new company added to the list.
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies + newCompany
        )
    }

    // Updates the status of a specific company.
    fun updateCompanyStatus(
        companyId: Int,
        newStatus: CompanyStatus
    ) {

        // Creates a new UI state with the updated company list.
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.map { company ->

                // Finds the company with the matching ID and creates an updated copy.
                if (company.id == companyId) {
                    company.copy(status = newStatus)
                } else {
                    company
                }
            }
        )
    }

    // Updates the name, category and website of a specific company.
    fun updateCompany(
        companyId: Int,
        name: String,
        category: String,
        websiteUrl: String?
    ) {
        // Creates a new UI state with the updated company information.
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.map { company ->

                // Finds the company to update and creates a copy with the new information.
                if (company.id == companyId) {
                    company.copy(
                        name = name,
                        category = category,
                        websiteUrl = websiteUrl
                    )
                } else {
                    company
                }
            }
        )
    }

    // Finds the company to update and creates a copy with the new information.
    fun deleteCompany(companyId: Int) {

        // Updates the UI state with the company removed from the list.
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.filter { company ->

                // Keeps all companies except the one with the matching ID.
                company.id != companyId
            }
        )
    }

}

