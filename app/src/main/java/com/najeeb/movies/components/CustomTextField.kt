package com.najeeb.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  hint: String = "Enter amount",
  hintStyle:TextStyle=MaterialTheme.typography.bodyMedium,
  backgroundColor: Color = Color.White,
  borderColor: Color = MaterialTheme.colorScheme.outline,
  enabled: Boolean = true
) {
  Box(modifier = modifier) {
    TextField(
      value = value,
      onValueChange = onValueChange,
      modifier = Modifier
        .fillMaxWidth()
        .background(backgroundColor)
        .border(
          width = 1.dp,
          color = if (enabled) borderColor else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
          shape = MaterialTheme.shapes.small
        ),
      singleLine = true,
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      colors = TextFieldDefaults.colors(
        focusedContainerColor = backgroundColor,
        unfocusedContainerColor = backgroundColor,
        disabledContainerColor = backgroundColor,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
      ),
      placeholder = { Text(text = hint, style = hintStyle) },
      trailingIcon = {
        if (value.isNotEmpty()) {
          IconButton(
            onClick = { onValueChange("") }
          ) {
            Icon(
              imageVector = Icons.Default.Clear,
              contentDescription = "Clear"
            )
          }
        }
      },
      enabled = enabled
    )
  }
}