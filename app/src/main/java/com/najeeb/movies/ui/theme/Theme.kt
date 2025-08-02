package com.najeeb.movies.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


val NewLightColorScheme = lightColorScheme(
  primary = NewPrimary,
  onPrimary = NewOnPrimary,
  primaryContainer = NewPrimaryContainer,
  onPrimaryContainer = NewOnPrimaryContainer,
  inversePrimary = NewInversePrimary,
  secondary = NewSecondary,
  onSecondary = NewOnSecondary,
  secondaryContainer = NewSecondaryContainer,
  onSecondaryContainer = NewOnSecondaryContainer,
  tertiary = NewTertiary,
  onTertiary = NewOnTertiary,
  tertiaryContainer = NewTertiaryContainer,
  onTertiaryContainer = NewOnTertiaryContainer,
  error = NewError,
  onError = NewOnError,
  errorContainer = NewErrorContainer,
  onErrorContainer = NewOnErrorContainer,
  background = NewBackground,
  onBackground = NewOnBackground,
  surface = NewSurface,
  onSurface = NewOnSurface,
  inverseSurface = NewInverseSurface,
  inverseOnSurface = NewInverseOnSurface,
  surfaceVariant = NewSurfaceVariant,
  onSurfaceVariant = NewOnSurfaceVariant,
  outline = NewOutline,
)

// A dark theme counterpart that matches the new design language.
val NewDarkColorScheme = darkColorScheme(
  primary = NewPrimary,
  onPrimary = NewOnPrimary,
  primaryContainer = NewPrimaryContainer,
  onPrimaryContainer = NewOnPrimaryContainer,
  inversePrimary = NewPrimary,
  secondary = NewSecondary,
  onSecondary = NewOnSecondary,
  secondaryContainer = NewSecondaryContainer,
  onSecondaryContainer = NewOnSecondaryContainer,
  tertiary = NewTertiary,
  onTertiary = NewOnTertiary,
  tertiaryContainer = NewTertiaryContainer,
  onTertiaryContainer = NewOnTertiaryContainer,
  error = NewError,
  onError = NewOnError,
  errorContainer = NewErrorContainer,
  onErrorContainer = NewOnErrorContainer,
  background = NewOnSurface, // Use the dark text color as background
  onBackground = NewOnPrimary,
  surface = NewSecondary, // Use a dark gray for surfaces
  onSurface = NewOnSecondary,
  inverseSurface = NewBackground,
  inverseOnSurface = NewOnBackground,
  surfaceVariant = NewOnSurfaceVariant,
  onSurfaceVariant = NewOnPrimary,
  outline = NewOutline,
)

@Composable
fun MoviesTheme(
  isDarkTheme: Boolean = isSystemInDarkTheme(),
  // The new design doesn't use dynamic colors, so let's disable it by default
  isDynamicColor: Boolean = false,
  content: @Composable () -> Unit
) {
  val myColorScheme = when {
    isDarkTheme -> NewDarkColorScheme
    else -> NewLightColorScheme
  }

  // This section is important for making the status bar match the new UI
  val view = LocalView.current
  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = myColorScheme.background.toArgb()
      window.navigationBarColor=myColorScheme.background.toArgb();
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
    }
  }

  MaterialTheme(
    colorScheme = myColorScheme,
    typography = replyTypography, // Assuming you have a typography file
    content = content,
  )
}