package com.example.emptyviewsactivity.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Indigo400,
    onPrimary = PureWhite,
    primaryContainer = Indigo700,
    onPrimaryContainer = Slate300,
    secondary = Cyan300,
    onSecondary = Slate900,
    secondaryContainer = Color(0xFF134E5B),
    onSecondaryContainer = Cyan300,
    tertiary = Coral200,
    onTertiary = Slate900,
    tertiaryContainer = Color(0xFF5F2A0C),
    onTertiaryContainer = Coral200,
    background = Slate900,
    onBackground = Mist,
    surface = Color(0xFF111827),
    onSurface = Mist,
    surfaceVariant = Color(0xFF1F2937),
    onSurfaceVariant = Slate300,
    outline = Slate600
)

private val LightColorScheme = lightColorScheme(
    primary = Indigo600,
    onPrimary = PureWhite,
    primaryContainer = Indigo400,
    onPrimaryContainer = Slate900,
    secondary = Cyan500,
    onSecondary = Slate900,
    secondaryContainer = Color(0xFFE0F7FF),
    onSecondaryContainer = Slate900,
    tertiary = Coral500,
    onTertiary = PureWhite,
    tertiaryContainer = Color(0xFFFFD7BA),
    onTertiaryContainer = Color(0xFF311200),
    background = Mist,
    onBackground = Slate900,
    surface = PureWhite,
    onSurface = Slate900,
    surfaceVariant = Slate200,
    onSurfaceVariant = Slate600,
    outline = Slate400
)

@Composable
fun EmptyViewsActivityTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
