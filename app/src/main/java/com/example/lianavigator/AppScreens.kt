package com.example.lianavigator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.draw.shadow
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material3.IconButton
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.clickable
import com.example.lianavigator.ui.theme.BlueGray


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: CompanyViewModel,
    onStatusSelected: (CompanyStatus) -> Unit
){
    val companies = viewModel.uiState.value.companies
    val totalCount = companies.size
    val savedCount = companies.count { it.status == CompanyStatus.SAVED }
    val ongoingCount = companies.count { it.status == CompanyStatus.CONTACT_ONGOING }
    val applicationCount = companies.count { it.status == CompanyStatus.APPLICATION_SENT }
    val confirmedCount = companies.count { it.status == CompanyStatus.LIA_CONFIRMED }


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
                title = "Företag",
                count = savedCount,
                totalCount = totalCount,
                icon = Icons.Default.Business,
                onClick = { onStatusSelected(CompanyStatus.SAVED) },
                modifier = Modifier.weight(1f),
                statusColor = companyStatusColor(
                    CompanyStatus.SAVED,
                    MaterialTheme.colorScheme.background == BlueGray
                ).second
            )

            StatCard(
                title = "Skickade ansökningar",
                count = applicationCount,
                icon = Icons.AutoMirrored.Filled.Send,
                onClick = { onStatusSelected(CompanyStatus.APPLICATION_SENT)},
                modifier = Modifier.weight(1f),
                statusColor = companyStatusColor(
                    CompanyStatus.APPLICATION_SENT,
                    MaterialTheme.colorScheme.background == BlueGray
                ).second
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatCard(
                title = "Pågående kontakter",
                count = ongoingCount,
                icon = Icons.Default.Groups,
                onClick = { onStatusSelected(CompanyStatus.CONTACT_ONGOING) },
                modifier = Modifier.weight(1f),
                statusColor = companyStatusColor(
                    CompanyStatus.CONTACT_ONGOING,
                    MaterialTheme.colorScheme.background == BlueGray
                ).second
            )

            StatCard(
                title = "Bekräftad LIA",
                count = confirmedCount,
                icon = Icons.Default.CheckCircle,
                onClick = { onStatusSelected(CompanyStatus.LIA_CONFIRMED)},
                modifier = Modifier.weight(1f),
                statusColor = companyStatusColor(
                    CompanyStatus.LIA_CONFIRMED,
                    MaterialTheme.colorScheme.background == BlueGray
                ).second
            )
        }
    }
}

@Composable
fun WelcomeCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(
            1.dp,
            TurquoiseGreen,
        ),
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
    totalCount: Int? = null,
    icon: ImageVector,
    statusColor: Color = TurquoiseGreen,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(148.dp)
            .clickable { onClick() },
        border = BorderStroke(
            1.dp,
            statusColor
        ),
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
                tint = statusColor
            )

            Text(
                text = if (totalCount != null) "$count av $totalCount" else count.toString(),
                style = MaterialTheme.typography.headlineMedium,
                color = statusColor
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
    viewModel: CompanyViewModel,
    selectedStatus: CompanyStatus? = null
) {
    val uiState = viewModel.uiState.value
    var companyName by remember { mutableStateOf("") }
    var companyCategory by remember { mutableStateOf("") }
    var companyWebsite by remember { mutableStateOf("") }
    var showAddDialog by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var categoryError by remember { mutableStateOf(false) }
    var editingCompanyId by remember {
        mutableStateOf<Int?>(null)
    }
    var showEditingMessage by remember {
        mutableStateOf(false)
    }
    val filteredCompanies = uiState.companies.filter { company ->

        val matchesSearch =
            company.name.startsWith(searchQuery, ignoreCase = true) ||
                    company.category.startsWith(searchQuery, ignoreCase = true)

        val matchesStatus =
            selectedStatus == null || company.status == selectedStatus

        matchesSearch && matchesStatus
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

        if (uiState.companies.isEmpty()) {
            Text(
                text = "Du har inte lagt till några företag ännu.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else if (filteredCompanies.isEmpty() && searchQuery.isNotBlank() &&
            selectedStatus == null) {
            Text(
                text = "Inga företag matchar din sökning. Prova ett annat sökord.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else if (filteredCompanies.isEmpty()) {
            Text(
                text = "Det finns inga företag med den här statusen ännu.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredCompanies) { company ->
                    CompanyCard(
                        company = company,
                        viewModel = viewModel,
                        editingCompanyId = editingCompanyId,
                        onEditingChange = {
                            editingCompanyId = it
                            showEditingMessage = false
                        },
                        showEditingMessage = showEditingMessage,
                        onEditingMessageChange = { show ->
                            showEditingMessage = show
                        }
                    )
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
                        onValueChange = {
                            companyName = it
                            nameError = false },
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
                        onValueChange = {
                            companyCategory = it
                            categoryError = false },
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
fun CompanyCard(
    company: Company,
    viewModel: CompanyViewModel,
    editingCompanyId: Int?,
    onEditingChange: (Int?) -> Unit,
    showEditingMessage: Boolean,
    onEditingMessageChange: (Boolean) -> Unit
) {
    val isDarkTheme =
        MaterialTheme.colorScheme.background == BlueGray

    val (backgroundColor, borderColor) =
        companyStatusColor(company.status, isDarkTheme)
    var expanded by remember { mutableStateOf(false) }

    val isEditing = editingCompanyId == company.id
    var showDeleteDialog by remember { mutableStateOf(false) }
    var editedName by remember { mutableStateOf(company.name) }
    var editedCategory by remember { mutableStateOf(company.category) }
    var editedWebsite by remember { mutableStateOf(company.websiteUrl ?: "") }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .clickable(
                enabled = editingCompanyId != null && !isEditing
            ) {
                onEditingMessageChange(true)
            }
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
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 4.dp
                ),
            verticalAlignment = Alignment.Top
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {
                if (isEditing) {
                    val textSelectionColors = TextSelectionColors(
                        handleColor = borderColor,
                        backgroundColor = borderColor.copy(alpha = 0.3f)
                    )

                    CompositionLocalProvider(
                        LocalTextSelectionColors provides textSelectionColors
                    ) {
                        OutlinedTextField(
                            value = editedName,
                            onValueChange = {
                                editedName = it
                                onEditingMessageChange(false)
                            },
                            label = { Text("Företagsnamn") },
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = borderColor,
                                unfocusedBorderColor = borderColor.copy(alpha = 0.6f),
                                cursorColor = borderColor,
                                focusedLabelColor = borderColor,
                                unfocusedLabelColor = borderColor
                            ),
                            shape = RoundedCornerShape(22.dp),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = editedCategory,
                            onValueChange = {
                                editedCategory = it
                                onEditingMessageChange(false)
                            },
                            label = { Text("Kategori") },
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = borderColor,
                                unfocusedBorderColor = borderColor.copy(alpha = 0.6f),
                                cursorColor = borderColor,
                                focusedLabelColor = borderColor,
                                unfocusedLabelColor = borderColor
                            ),
                            shape = RoundedCornerShape(22.dp),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = editedWebsite,
                            onValueChange = {
                                editedWebsite = it
                                onEditingMessageChange(false)
                            },
                            label = { Text("Webbplats") },
                            textStyle = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurface
                            ),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = borderColor,
                                unfocusedBorderColor = borderColor.copy(alpha = 0.6f),
                                cursorColor = borderColor,
                                focusedLabelColor = borderColor,
                                unfocusedLabelColor = borderColor
                            ),
                            shape = RoundedCornerShape(22.dp),
                            singleLine = true
                        )
                    }

                    if (isEditing && showEditingMessage) {
                        Text(
                            text = "Vänligen spara eller avbryt om du vill gå vidare.",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(
                            onClick = {
                                editedName = company.name
                                editedCategory = company.category
                                editedWebsite = company.websiteUrl ?: ""
                                onEditingChange(null)
                            },
                            colors = ButtonDefaults.textButtonColors(
                                contentColor = borderColor
                            )
                        ) {
                            Text("Avbryt")
                        }

                        Button(
                            onClick = {
                                viewModel.updateCompany(
                                    companyId = company.id,
                                    name = editedName.trim(),
                                    category = editedCategory.trim(),
                                    websiteUrl = editedWebsite.trim().ifBlank { null }
                                )
                                onEditingChange(null)
                            },
                            enabled = editedName.isNotBlank() &&
                                    editedCategory.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = borderColor.copy(alpha = 0.15f),
                                contentColor = borderColor,
                                disabledContainerColor = borderColor.copy(alpha = 0.08f),
                                disabledContentColor = borderColor.copy(alpha = 0.5f)
                            )
                        ) {
                            Text("Spara")
                        }
                    }
                } else {
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
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (!isEditing) {
                    Surface(
                        modifier = Modifier.clickable {
                            expanded = true
                        },
                        color = borderColor.copy(alpha = 0.15f),
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
                            text = {
                                Text(
                                    text = "Sparat företag",
                                    color = companyStatusColor(CompanyStatus.SAVED).second
                                )
                            },
                            onClick = {
                                viewModel.updateCompanyStatus(
                                    company.id,
                                    CompanyStatus.SAVED
                                )
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Ansökan skickad",
                                    color = companyStatusColor(CompanyStatus.APPLICATION_SENT).second
                                )
                            },
                            onClick = {
                                viewModel.updateCompanyStatus(
                                    company.id,
                                    CompanyStatus.APPLICATION_SENT
                                )
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "Kontakt pågår",
                                    color = companyStatusColor(CompanyStatus.CONTACT_ONGOING).second
                                )
                            },
                            onClick = {
                                viewModel.updateCompanyStatus(
                                    company.id,
                                    CompanyStatus.CONTACT_ONGOING
                                )
                                expanded = false
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = "LIA bekräftad",
                                    color = companyStatusColor(CompanyStatus.LIA_CONFIRMED).second
                                )
                            },
                            onClick = {
                                viewModel.updateCompanyStatus(
                                    company.id,
                                    CompanyStatus.LIA_CONFIRMED
                                )
                                expanded = false
                            }
                        )
                    }

                if (!isEditing) {
                    OutlinedButton(
                        onClick = {
                            if (editingCompanyId != null) {
                                onEditingMessageChange(true)
                            } else {
                                editedName = company.name
                                editedCategory = company.category
                                editedWebsite = company.websiteUrl ?: ""
                                onEditingChange(company.id)
                            }
                        },

                        modifier = Modifier.height(20.dp),
                        border = null,
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = borderColor.copy(alpha = 0.15f),
                            contentColor = borderColor
                        ),
                        contentPadding = PaddingValues(
                            horizontal = 10.dp,
                            vertical = 0.dp
                        )
                    ) {
                        Text(
                            text = "Redigera",
                            color = borderColor,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
                }
            }
        }

        if (!isEditing) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(
                    onClick = {
                        showDeleteDialog = true
                    },
                    modifier = Modifier.size(28.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.trash_bin),
                        contentDescription = "Ta bort företag",
                        modifier = Modifier.size(16.dp),
                        colorFilter = ColorFilter.tint(borderColor)
                    )
                }
            }
        }

    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            },
            title = {
                Text("Ta bort företag?")
            },
            text = {
                Text("Vill du ta bort ${company.name} från din företagslista? Åtgärden kan inte ångras.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.deleteCompany(company.id)
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CelebrationRed.copy(alpha = 0.70f),
                        contentColor = Color.White
                    )
                ) {
                    Text("Ta bort")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                    }
                ) {
                    Text("Avbryt")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface
        )
    }
}

fun companyStatusColor(
    status: CompanyStatus,
    isDarkTheme: Boolean = false
): Pair<Color, Color>{

    val statusColor = when (status) {
        CompanyStatus.SAVED -> TurquoiseGreen
        CompanyStatus.CONTACT_ONGOING -> Lavender
        CompanyStatus.APPLICATION_SENT -> Color(0xFF7895C5)
        CompanyStatus.LIA_CONFIRMED -> CelebrationRed
    }

    val backgroundColor = if (isDarkTheme) {
        Color(0xFF465365)
    } else {
        when (status) {
            CompanyStatus.SAVED -> LightTurquoise
            CompanyStatus.CONTACT_ONGOING -> LightLavender
            CompanyStatus.APPLICATION_SENT -> Color(0xFFEEF3FF)
            CompanyStatus.LIA_CONFIRMED -> LightCelebrationRed
        }
    }

    return backgroundColor to statusColor
}

            fun companyStatusText(status: CompanyStatus): String {
                return when (status) {
                    CompanyStatus.SAVED -> "Sparat företag"
                    CompanyStatus.APPLICATION_SENT -> "Ansökan skickad"
                    CompanyStatus.CONTACT_ONGOING -> "Kontakt pågår"
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
