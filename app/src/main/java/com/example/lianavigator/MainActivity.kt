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
import androidx.compose.runtime.mutableStateOf
import com.example.lianavigator.ui.theme.BlueGray
import com.example.lianavigator.ui.theme.LightTurquoise
import com.example.lianavigator.viewmodel.CompanyViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lianavigator.data.CompanyStatus
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

// Main entry point of the application.
// Sets up the app theme, navigation and screen layout.
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Allows the app content to extend behind system bars.
        enableEdgeToEdge()
        // Defines the user interface using Jetpack Compose.
        setContent {
            // Creates or retrieves the ViewModel used to manage company data.
            val companyViewModel: CompanyViewModel = viewModel()

            // Applies the app's custom colors, typography and theme.
            LIANavigatorTheme {

                // Stores the currently selected bottom navigation item.
                // The selection is preserved during configuration changes.
                var selectedItem by rememberSaveable {
                    mutableIntStateOf(0)
                }

                // Stores the company status selected on the home screen.
                // Null means that no specific status filter is selected.
                var selectedStatus by rememberSaveable {
                    mutableStateOf<CompanyStatus?>(null)
                }

                // Provides the basic app layout with a top bar and bottom navigation.
                Scaffold(
                    modifier = Modifier.fillMaxSize(),

                    // Displays the app logo and profile button at the top.
                    topBar = {
                        TopAppBar(
                            title = {
                                Image(
                                    painter = painterResource(R.drawable.lia_navigator_logo),
                                    contentDescription = "LIA Navigator",
                                    modifier = Modifier.height(100.dp),
                                    contentScale = ContentScale.Fit
                                )
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

                    // Provides navigation between the app's main screens.
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary,
                        ) {
                            // Home screen
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

                            //Company screen
                            NavigationBarItem(
                                selected = selectedItem == 1,
                                onClick = {
                                    selectedItem = 1
                                    selectedStatus = null},
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

                            //Collaboration screen
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

                            //Profile screen
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

                    // Displays the screen corresponding to the selected navigation item.
                    // Passes shared data and callbacks to the relevant screen.
                    when (selectedItem) {

                        // Home screen: selecting a company status opens the company screen
                        // with the selected status as a filter.
                        0 -> HomeScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = companyViewModel,
                            onStatusSelected = { status ->
                                selectedStatus = status
                                selectedItem = 1
                            }
                        )

                        // Company screen: displays and manages the company list.
                        1 -> CompanyScreen(
                            modifier = Modifier.padding(innerPadding),
                            viewModel = companyViewModel,
                            selectedStatus = selectedStatus
                        )

                        // Collaboration screen (currently a placeholder).
                        2 -> CollaborationScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                        // Profile screen (currently a placeholder).
                        3 -> ProfileScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

