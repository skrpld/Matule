package com.skrpld.matule.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Accent,
    onPrimary = White,
    secondary = AccentInactive,
    onSecondary = White,
    inversePrimary = AccentInactive,
    background = White,
    surface = InputBackground,
    surfaceVariant = InputString
)

@Composable
fun MatuleTheme(
    content: @Composable () -> Unit
) {
    val colorScheme =  LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}