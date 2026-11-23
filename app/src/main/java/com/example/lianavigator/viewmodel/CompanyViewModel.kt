package com.example.lianavigator.viewmodel

import com.example.lianavigator.data.Company
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lianavigator.data.CompanyStatus
import com.example.lianavigator.ui.CompanyUiState

class CompanyViewModel : ViewModel() {
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
    private val _uiState = mutableStateOf(
        CompanyUiState(
            companies = testCompany
        )
    )

    val uiState: State<CompanyUiState> = _uiState

    fun addCompany(name: String, category: String, websiteUrl: String? = null) {
        val newId = (_uiState.value.companies.maxOfOrNull { it.id } ?: 0) + 1

        val newCompany = Company(
            id = newId,
            name = name,
            category = category,
            websiteUrl = websiteUrl
        )

        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies + newCompany
        )
    }

    fun updateCompanyStatus(
        companyId: Int,
        newStatus: CompanyStatus
    ) {
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.map { company ->
                if (company.id == companyId) {
                    company.copy(status = newStatus)
                } else {
                    company
                }
            }
        )
    }

    fun updateCompany(
        companyId: Int,
        name: String,
        category: String,
        websiteUrl: String?
    ) {
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.map { company ->
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

    fun deleteCompany(companyId: Int) {
        _uiState.value = _uiState.value.copy(
            companies = _uiState.value.companies.filter { company ->
                company.id != companyId
            }
        )
    }

}

