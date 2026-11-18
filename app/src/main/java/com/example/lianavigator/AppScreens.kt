package com.example.lianavigator

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Välkommen till LIA Navigator!",
        modifier = modifier
    )
}

@Composable
fun CompanyScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Företag",
        modifier = modifier
    )
}

@Composable
fun CollaborationScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Samarbeta",
        modifier = modifier
    )
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Profil",
        modifier = modifier
    )
}