package com.najeeb.movies.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BaseOutlinedButton(
  title: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  OutlinedButton(
    border = BorderStroke(
      color = MaterialTheme.colorScheme.primary,
      width = 0.5.dp,
    ),
    modifier = modifier
      .fillMaxWidth()
      .height(70.dp)
      .padding(vertical = 8.dp),
    onClick = onClick
  ) {
    Text(
      text = title, style = MaterialTheme.typography.titleMedium.copy(
        color = MaterialTheme.colorScheme.primary
      )
    )
  }
}