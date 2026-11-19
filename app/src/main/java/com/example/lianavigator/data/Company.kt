package com.example.lianavigator.data

data class Company(
    val id: Int,
    val name: String,
    val category: String,
    val websiteUrl: String? = null,
    val isSaved: Boolean = false)

