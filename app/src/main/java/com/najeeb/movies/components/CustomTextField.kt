package com.najeeb.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
  hintStyle: TextStyle = MaterialTheme.typography.bodyMedium,
  backgroundColor: Color = MaterialTheme.colorScheme.background,
  borderColor: Color = MaterialTheme.colorScheme.outline,
  enabled: Boolean = true,
  enableClearButton: Boolean = false,
) {
  Box(modifier = modifier) {
    OutlinedTextField(
      value = value,
      onValueChange = onValueChange,
      modifier = Modifier
        .fillMaxWidth()
        .background(backgroundColor),

      singleLine = true,
      shape = RoundedCornerShape(8.dp),
      keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
      colors = TextFieldDefaults.colors(
        focusedContainerColor = backgroundColor,
        unfocusedContainerColor = backgroundColor,
        disabledContainerColor = backgroundColor,
        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
        unfocusedIndicatorColor = borderColor,
        disabledIndicatorColor = Color.Transparent
      ),
      placeholder = { Text(text = hint, style = hintStyle) },
      trailingIcon = {
        if (value.isNotEmpty()&&enableClearButton) {
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