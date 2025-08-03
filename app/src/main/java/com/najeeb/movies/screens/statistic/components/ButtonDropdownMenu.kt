package com.najeeb.movies.screens.statistic.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ButtonDropdownMenu() {
  var expanded by remember { mutableStateOf(false) }
  val items = listOf("Expense", "Income")

  Box(modifier = Modifier.wrapContentSize()) {
    OutlinedButton(
      contentPadding = PaddingValues(
        vertical = 8.dp,
        horizontal = 20.dp
      ),
      onClick = { expanded = true },
      modifier = Modifier.size(width = 120.dp, height = 40.dp),
      shape = RoundedCornerShape(10.dp),
    ) {
      Row {
        Text(
          "Expense",
          style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.typography.bodySmall.color,
          ),
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
          Icons.Default.KeyboardArrowDown, contentDescription = "More options",

          modifier = Modifier.size(20.dp),
          tint = MaterialTheme.typography.bodySmall.color,
        )
      }
    }

    DropdownMenu(
      modifier = Modifier.background(
        color = Color.White
      ),
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      items.forEach { item ->
        DropdownMenuItem(
          text = { Text(item) },
          onClick = {
            println("Selected: $item")
            expanded = false
          }
        )
      }
    }
  }
}
