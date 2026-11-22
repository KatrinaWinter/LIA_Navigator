package com.example.lianavigator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import com.example.lianavigator.data.Company
import com.example.lianavigator.ui.theme.Lavender
import com.example.lianavigator.viewmodel.CompanyViewModel
import androidx.compose.runtime.setValue
import com.example.lianavigator.ui.theme.TurquoiseGreen
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.TextButton
import com.example.lianavigator.data.CompanyStatus
import com.example.lianavigator.ui.theme.LightTurquoise
import com.example.lianavigator.ui.theme.LightLavender
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Surface
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import com.example.lianavigator.ui.theme.CelebrationRed
import com.example.lianavigator.ui.theme.Cream
import com.example.lianavigator.ui.theme.LightCelebrationRed
import androidx.compose.foundation.layout.Box


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(20.dp)) {
        ScreenHeader(title = "Översikt")

        WelcomeCard()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Din LIA-översikt",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Sparade företag",
                count = 8,
                icon = Icons.Default.Business,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Pågående kontakter",
                count = 3,
                icon = Icons.Default.Groups,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Skickade ansökningar",
                count = 2,
                icon = Icons.AutoMirrored.Filled.Send,
                modifier = Modifier.weight(1f)
            )

            StatCard(
                title = "Bekräftad LIA",
                count = 0,
                icon = Icons.Default.CheckCircle,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Välkommen till LIA Navigator! 🌿",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "Här samlar du företag, kontakter och dina ansökningar på ett och samma ställe.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
fun StatCard(
    title: String,
    count: Int,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(148.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )

            Text(
                text = count.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}



@Composable
fun CompanyScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyViewModel
) {
    val uiState = viewModel.uiState.value
    var companyName by remember { mutableStateOf("") }
    var companyCategory by remember { mutableStateOf("") }
    var companyWebsite by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var categoryError by remember { mutableStateOf(false) }
    val filteredCompanies = uiState.companies.filter { company ->
        company.name.startsWith(searchQuery, ignoreCase = true) ||
                company.category.startsWith(searchQuery, ignoreCase = true)
    }

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        ScreenHeader(title = "Företag")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledIconButton(
                onClick = { showAddDialog = true },
                modifier = Modifier.size(28.dp),
                shape = CircleShape,
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = TurquoiseGreen,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Lägg till företag"
                )
            }

            Text(
                text = "Lägg till företag",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Text(
                    "Sök bland sparade företag...",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Sök"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(50.dp),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            textStyle = MaterialTheme.typography.bodyMedium

        )

        Spacer(modifier = Modifier.height(12.dp))

        if (filteredCompanies.isEmpty()) {
            Text(
                text = "Inga företag hittades. Prova ett annat sökord.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredCompanies) { company ->
                    CompanyCard(company = company,
                        viewModel= viewModel)
                }
            }
        }
    }

    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = {
                showAddDialog = false
            },
            title = {
                Text("Lägg till företag",)
            },
            containerColor = MaterialTheme.colorScheme.surface,
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
                        label = { Text("Företagsnamn",
                            style = MaterialTheme.typography.bodyMedium) },
                        isError = nameError,
                        supportingText = {
                            if (nameError) {
                                Text("Fyll i företagsnamn.")
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp)
                    )

                    OutlinedTextField(
                        value = companyCategory,
                        onValueChange = { companyCategory = it },
                        label = { Text("Kategori",
                            style = MaterialTheme.typography.bodyMedium) },
                        isError = categoryError,
                        supportingText = {
                            if (categoryError) {
                                Text("Fyll i kategori.")
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp)
                    )

                    OutlinedTextField(
                        value = companyWebsite,
                        onValueChange = { companyWebsite = it },
                        label = { Text("Webbplats (valfritt)",
                            style = MaterialTheme.typography.bodyMedium) },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp)
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        nameError = companyName.isBlank()
                        categoryError = companyCategory.isBlank()

                        if (!nameError && !categoryError) {
                            viewModel.addCompany(
                                name = companyName,
                                category = companyCategory,
                                websiteUrl = companyWebsite.takeIf { it.isNotBlank() }
                            )

                            companyName = ""
                            companyCategory = ""
                            companyWebsite = ""
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Spara företag")
                }
            }
        )
    }

}

@Composable
fun CompanyCard(company: Company,
                viewModel: CompanyViewModel) {
    val (backgroundColor, borderColor) = companyStatusColor(company.status)
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        val uriHandler = LocalUriHandler.current

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = company.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = borderColor
                )

                Text(
                    text = company.category,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                company.websiteUrl?.let { url ->
                    Text(
                        text = url,
                        modifier = Modifier.clickable {
                            uriHandler.openUri(url)
                        },
                        textDecoration = TextDecoration.Underline,
                        style = MaterialTheme.typography.bodySmall,
                        color = Lavender
                    )

                }
            }

            Box {
                Surface(
                    modifier = Modifier.clickable {
                        expanded = true
                    },
                    color = backgroundColor,
                    shape = RoundedCornerShape(50.dp),
                    border = BorderStroke(1.dp, borderColor)
                ) {
                    Text(
                        text = companyStatusText(company.status),
                        modifier = Modifier.padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        ),
                        style = MaterialTheme.typography.labelMedium,
                        color = borderColor
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier.width(190.dp),
                    containerColor = MaterialTheme.colorScheme.surface
                ) {
                DropdownMenuItem(
                    text = { Text("Sparat företag") },
                    onClick = {
                        viewModel.updateCompanyStatus(
                            company.id,
                            CompanyStatus.SAVED
                        )
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Kontakt pågår") },
                    onClick = {
                        viewModel.updateCompanyStatus(
                            company.id,
                            CompanyStatus.CONTACT_ONGOING
                        )
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("Ansökan skickad") },
                    onClick = {
                        viewModel.updateCompanyStatus(
                            company.id,
                            CompanyStatus.APPLICATION_SENT
                        )
                        expanded = false
                    }
                )

                DropdownMenuItem(
                    text = { Text("LIA bekräftad") },
                    onClick = {
                        viewModel.updateCompanyStatus(
                            company.id,
                            CompanyStatus.LIA_CONFIRMED
                        )
                        expanded = false
                    }
                )
            }
            }
        }
    }
}

            fun companyStatusColor(status: CompanyStatus): Pair<Color, Color> {
                return when (status) {
                    CompanyStatus.SAVED ->
                        LightTurquoise to TurquoiseGreen

                    CompanyStatus.CONTACT_ONGOING ->
                        LightLavender to Lavender

                    CompanyStatus.APPLICATION_SENT ->
                        Color(0xFFEEF3FF) to Color(0xFF7895C5)

                    CompanyStatus.LIA_CONFIRMED ->
                        LightCelebrationRed to CelebrationRed
                }
            }

            fun companyStatusText(status: CompanyStatus): String {
                return when (status) {
                    CompanyStatus.SAVED -> "Sparat företag"
                    CompanyStatus.CONTACT_ONGOING -> "Kontakt pågår"
                    CompanyStatus.APPLICATION_SENT -> "Ansökan skickad"
                    CompanyStatus.LIA_CONFIRMED -> "LIA bekräftad"
                }
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

            @Composable
            fun ScreenHeader(
                title: String,
                modifier: Modifier = Modifier) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Spacer(modifier = Modifier.width(12.dp))



                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                }
            }
