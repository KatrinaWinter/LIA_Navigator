
package com.example.lianavigator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Dark theme
private val DarkColorScheme = darkColorScheme(
    primary = SageGreen,
    secondary = SoftForestGreen,
    tertiary = Lilac,
    background = Color(0xFF28332F),
    surface = Color(0xFF394640),
    onPrimary = Charcoal,
    onSecondary = Cream,
    onTertiary = Charcoal,
    onBackground = Cream,
    onSurface = Cream,
    onSurfaceVariant = Color(0xFFB8C9BC),
)

// Light theme
private val LightColorScheme = lightColorScheme(
    primary = SoftForestGreen,
    secondary = SageGreen,
    tertiary = Lilac,
    background = Cream,
    surface = CardWhite,
    onPrimary = CardWhite,
    onSecondary = ForestGreen,
    onTertiary = Charcoal,
    onBackground = Charcoal,
    onSurface = Charcoal,
    onSurfaceVariant = MutedGreen,
)

@Composable
fun LIANavigatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
