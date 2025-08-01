package com.najeeb.movies.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun GradientButton(
  text: String,
  gradient: Brush? = null,
  @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  ElevatedButton(
    modifier = modifier
      .fillMaxWidth()
      .height(70.dp)
      .padding(horizontal = 16.dp, vertical = 8.dp)
      .shadow(
        shape = RoundedCornerShape(50.dp),
        clip = false,
        elevation = 20.dp,
        ambientColor = MaterialTheme.colorScheme.primary,
        spotColor = MaterialTheme.colorScheme.primary
      ),
    elevation = ButtonDefaults.buttonElevation(defaultElevation = 15.dp),
    colors = ButtonDefaults.buttonColors(
      containerColor = Color.Transparent,

    ),
    contentPadding = PaddingValues(),
    onClick = onClick,
  ) {
    Box(
      modifier = Modifier
        .background(
          gradient ?: Brush.verticalGradient(
            colors = listOf(
              MaterialTheme.colorScheme.onPrimary,
              MaterialTheme.colorScheme.primary,
            ),
            startY = 1f,
            endY = 180f,
          ),
        )
        .fillMaxSize()
        .then(modifier),

      contentAlignment = Alignment.Center,
    ) {
      Text(
        text = text,
        style = MaterialTheme.typography.titleLarge.copy(
          color = Color.White
        )

      )
    }
  }
}
