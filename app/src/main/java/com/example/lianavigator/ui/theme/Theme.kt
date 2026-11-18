
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
    secondary = Lilac,
    tertiary = Cream,
    background = Charcoal,
    surface = Color(0xFF414B46),
    onPrimary = Charcoal,
    onSecondary = Charcoal,
    onTertiary = Charcoal,
    onBackground = Cream,
    onSurface = Cream
)

// Light theme
private val LightColorScheme = lightColorScheme(
    primary = ForestGreen,
    secondary = SageGreen,
    tertiary = Lilac,
    background = Cream,
    surface = CardWhite,
    onPrimary = CardWhite,
    onSecondary = Charcoal,
    onTertiary = Charcoal,
    onBackground = Charcoal,
    onSurface = Charcoal
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
