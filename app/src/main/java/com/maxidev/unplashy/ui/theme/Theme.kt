package com.maxidev.unplashy.ui.theme

import DarkBackground
import DarkError
import DarkErrorContainer
import DarkInverseOnSurface
import DarkInversePrimary
import DarkInverseSurface
import DarkOnBackground
import DarkOnError
import DarkOnErrorContainer
import DarkOnPrimary
import DarkOnPrimaryContainer
import DarkOnSecondary
import DarkOnSecondaryContainer
import DarkOnSurface
import DarkOnSurfaceVariant
import DarkOnTertiary
import DarkOnTertiaryContainer
import DarkOutline
import DarkOutlineVariant
import DarkPrimary
import DarkPrimaryContainer
import DarkScrim
import DarkSecondary
import DarkSecondaryContainer
import DarkSurface
import DarkSurfaceBright
import DarkSurfaceContainer
import DarkSurfaceContainerHigh
import DarkSurfaceContainerHighest
import DarkSurfaceContainerLow
import DarkSurfaceContainerLowest
import DarkSurfaceDim
import DarkSurfaceTint
import DarkSurfaceVariant
import DarkTertiary
import DarkTertiaryContainer
import PastelBackground
import PastelError
import PastelErrorContainer
import PastelInverseOnSurface
import PastelInversePrimary
import PastelInverseSurface
import PastelOnBackground
import PastelOnError
import PastelOnErrorContainer
import PastelOnPrimary
import PastelOnPrimaryContainer
import PastelOnSecondary
import PastelOnSecondaryContainer
import PastelOnSurface
import PastelOnSurfaceVariant
import PastelOnTertiary
import PastelOnTertiaryContainer
import PastelOutline
import PastelOutlineVariant
import PastelPrimary
import PastelPrimaryContainer
import PastelScrim
import PastelSecondary
import PastelSecondaryContainer
import PastelSurface
import PastelSurfaceBright
import PastelSurfaceContainer
import PastelSurfaceContainerHigh
import PastelSurfaceContainerHighest
import PastelSurfaceContainerLow
import PastelSurfaceContainerLowest
import PastelSurfaceDim
import PastelSurfaceTint
import PastelSurfaceVariant
import PastelTertiary
import PastelTertiaryContainer
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    tertiary = DarkTertiary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    inversePrimary = DarkInversePrimary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
    surfaceTint = DarkSurfaceTint,
    inverseSurface = DarkInverseSurface,
    inverseOnSurface = DarkInverseOnSurface,
    error = DarkError,
    onError = DarkOnError,
    errorContainer = DarkErrorContainer,
    onErrorContainer = DarkOnErrorContainer,
    outline = DarkOutline,
    outlineVariant = DarkOutlineVariant,
    scrim = DarkScrim,
    surfaceBright = DarkSurfaceBright,
    surfaceContainer = DarkSurfaceContainer,
    surfaceContainerHigh = DarkSurfaceContainerHigh,
    surfaceContainerHighest = DarkSurfaceContainerHighest,
    surfaceContainerLow = DarkSurfaceContainerLow,
    surfaceContainerLowest = DarkSurfaceContainerLowest,
    surfaceDim = DarkSurfaceDim
)

val LightColorScheme = lightColorScheme(
    primary = PastelPrimary,
    secondary = PastelSecondary,
    tertiary = PastelTertiary,
    onPrimary = PastelOnPrimary,
    primaryContainer = PastelPrimaryContainer,
    onPrimaryContainer = PastelOnPrimaryContainer,
    inversePrimary = PastelInversePrimary,
    onSecondary = PastelOnSecondary,
    secondaryContainer = PastelSecondaryContainer,
    onSecondaryContainer = PastelOnSecondaryContainer,
    onTertiary = PastelOnTertiary,
    tertiaryContainer = PastelTertiaryContainer,
    onTertiaryContainer = PastelOnTertiaryContainer,
    background = PastelBackground,
    onBackground = PastelOnBackground,
    surface = PastelSurface,
    onSurface = PastelOnSurface,
    surfaceVariant = PastelSurfaceVariant,
    onSurfaceVariant = PastelOnSurfaceVariant,
    surfaceTint = PastelSurfaceTint,
    inverseSurface = PastelInverseSurface,
    inverseOnSurface = PastelInverseOnSurface,
    error = PastelError,
    onError = PastelOnError,
    errorContainer = PastelErrorContainer,
    onErrorContainer = PastelOnErrorContainer,
    outline = PastelOutline,
    outlineVariant = PastelOutlineVariant,
    scrim = PastelScrim,
    surfaceBright = PastelSurfaceBright,
    surfaceContainer = PastelSurfaceContainer,
    surfaceContainerHigh = PastelSurfaceContainerHigh,
    surfaceContainerHighest = PastelSurfaceContainerHighest,
    surfaceContainerLow = PastelSurfaceContainerLow,
    surfaceContainerLowest = PastelSurfaceContainerLowest,
    surfaceDim = PastelSurfaceDim
)

@Composable
fun UnplashyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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