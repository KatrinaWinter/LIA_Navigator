package com.example.lianavigator

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import com.example.lianavigator.data.Company
import com.example.lianavigator.ui.theme.Lilac
import com.example.lianavigator.viewmodel.CompanyViewModel


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

    Column(
        modifier = modifier.padding(20.dp)
    ) {
        ScreenHeader(title = "Företag")

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(uiState.companies) { company ->
            CompanyCard(company = company)
        }
    }
    }
}

@Composable
fun CompanyCard(company: Company) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        val uriHandler = LocalUriHandler.current

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = company.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
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
                    color = Lilac
                )
            }
        }
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
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /*Text(
            text = "Ⓛ",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )*/

        Spacer(modifier = Modifier.width(12.dp))



            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

        Spacer(modifier = Modifier.height(20.dp))

    }
}