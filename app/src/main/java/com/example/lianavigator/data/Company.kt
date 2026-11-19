package com.example.lianavigator.data

data class Company(
    val id: Int,
    val name: String,
    val category: String,
    val isSaved: Boolean = false)

