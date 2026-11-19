package com.example.lianavigator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lianavigator.ui.theme.LIANavigatorTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.remember
import com.example.lianavigator.ui.theme.MutedGreen
import com.example.lianavigator.ui.theme.SageGreen
import com.example.lianavigator.ui.theme.SoftForestGreen
import com.example.lianavigator.viewmodel.CompanyViewModel

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LIANavigatorTheme {
                var selectedItem by rememberSaveable {
                    mutableIntStateOf(0)
                }
                val companyViewModel = remember {
                    CompanyViewModel()
                }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Ⓛ LIA Navigator")
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Default.AccountCircle,
                                        contentDescription = "Profil"
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.background,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                                actionIconContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            NavigationBarItem(
                                selected = selectedItem == 0,
                                onClick = { selectedItem = 0 },
                                icon = {
                                    Icon(Icons.Default.Home, contentDescription = "Hem")
                                },
                                label = { Text("Hem") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)
                                )
                            )

                            NavigationBarItem(
                                selected = selectedItem == 1,
                                onClick = { selectedItem = 1 },
                                icon = {
                                    Icon(Icons.Default.Business, contentDescription = "Företag")
                                },
                                label = { Text("Företag") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)
                                )
                            )

                            NavigationBarItem(
                                selected = selectedItem == 2,
                                onClick = { selectedItem = 2 },
                                icon = {
                                    Icon(Icons.Default.Groups, contentDescription = "Samarbeta")
                                },
                                label = { Text("Samarbeta") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)
                                )
                            )

                            NavigationBarItem(
                                selected = selectedItem == 3,
                                onClick = { selectedItem = 3 },
                                icon = {
                                    Icon(Icons.Default.AccountCircle, contentDescription = "Profil")
                                },
                                label = { Text("Profil") },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = MaterialTheme.colorScheme.primary,
                                    selectedTextColor = MaterialTheme.colorScheme.primary,
                                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.20f)
                                )
                            )
                        }
                    }
                ) { innerPadding ->
                    when (selectedItem) {
                        0 -> HomeScreen(
                            modifier = Modifier.padding(innerPadding))
                        1 -> CompanyScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = companyViewModel)
                        2 -> CollaborationScreen(
                            modifier = Modifier.padding(innerPadding))
                        3 -> ProfileScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}
