package com.najeeb.movies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


// Redefine colors to match the new UI/UX design from the images
val NewPrimary = Color(0xFF2A7C76)
val NewOnPrimary = Color(0xFF69AEA9)
val NewPrimaryContainer = Color(0xFF69AEA9)
val NewOnPrimaryContainer = Color(0xFF191C1D)

val NewSecondary = Color(0xFF2D3132) // Used for active bottom nav icons
val NewOnSecondary = Color(0xFFFFFFFF)
val NewSecondaryContainer = Color(0xFFE0E3E3) // Used for inactive bottom nav icons
val NewOnSecondaryContainer = Color(0xFF191C1D)

val NewTertiary = Color(0xFFFBC02D) // Notification dot
val NewOnTertiary = Color(0xFF191C1D)
val NewTertiaryContainer = Color(0xFFFFFBE5)
val NewOnTertiaryContainer = Color(0xFF261900)

val NewError = Color(0xFFBA1B1B) // Red for expenses
val NewOnError = Color(0xFFFFFFFF)
val NewErrorContainer = Color(0xFFFFDAD4)
val NewOnErrorContainer = Color(0xFF410001)

val NewBackground = Color(0xFFFBFDFD)
val NewOnBackground = Color(0xFF191C1D)
val NewSurface = "#f6f6f6".toColor()
var GrayColor = "#666666".toColor()
val NewOnSurface = Color(0xFF191C1D)
val NewSurfaceVariant = Color(0xFFE0E3E3) // For dividers, light backgrounds
val NewOnSurfaceVariant = Color(0xFF45464F) // For secondary text on surfaces

val NewOutline = Color(0xFFC4C7C7)
val NewInverseSurface = Color(0xFF2D3132)
val NewInverseOnSurface = Color(0xFFFBFDFD)
val NewInversePrimary = Color(0xFFB8C3FF) // Inverse of primary

val NewPositive = Color(0xFF00C853) // Green for income

val BackgroundCardColor: Color
  @Composable get() = if (isSystemInDarkTheme()) Color.Gray.copy(alpha = 0.1f) else "#F0F6F5".toColor()

val ActiveTextToggleButtonColor: Color
  @Composable get() = if (isSystemInDarkTheme()) MaterialTheme.typography.titleLarge.color else GrayColor




val BlackAndWhite: Color
  @Composable get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val IconColor: Color
  @Composable get() = if (isSystemInDarkTheme()) Color.White else Color(0xFF2D3132)

fun String.toColor(): Color {
  return Color(android.graphics.Color.parseColor(this))
}