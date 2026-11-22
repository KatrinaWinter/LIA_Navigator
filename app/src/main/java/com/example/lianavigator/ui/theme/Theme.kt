
package com.example.lianavigator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// Dark theme
private val DarkColorScheme = darkColorScheme(
    primary = LightTurquoise,
    secondary = Lavender,
    tertiary = LightLavender,
    background = BlueGray,
    surface = Color(0xFF465365),
    onPrimary = BlueGray,
    onSecondary = BlueGray,
    onTertiary = BlueGray,
    onBackground = Cream,
    onSurface = Cream,
    onSurfaceVariant = LightTurquoise
)

// Light theme
private val LightColorScheme = lightColorScheme(
    primary = TurquoiseGreen,
    secondary = Lavender,
    tertiary = LightLavender,
    background = Cream,
    surface = CardWhite,
    onPrimary = CardWhite,
    onSecondary = BlueGray,
    onTertiary = BlueGray,
    onBackground = BlueGray,
    onSurface = BlueGray,
    onSurfaceVariant = BlueGray
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
