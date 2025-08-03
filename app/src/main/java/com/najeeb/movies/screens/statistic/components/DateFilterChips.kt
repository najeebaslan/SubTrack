package com.najeeb.movies.screens.statistic.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.najeeb.movies.ui.theme.GrayColor


@Composable
fun DateFilterChips(
  dates: List<String> = listOf("Day", "Week", "Month", "Year"),
  onDateSelected: (String) -> Unit = {}
) {
  var selectedDate by remember { mutableStateOf(dates[0]) }

  Row(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .horizontalScroll(rememberScrollState()),
    horizontalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    dates.forEach { date ->
      Box(
        modifier = Modifier.width(IntrinsicSize.Min),
        contentAlignment = Alignment.Center
      ) {
        FilterChip(
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier
            .height(40.dp)
            .fillMaxWidth(),
          onClick = {
            selectedDate = date
            onDateSelected(date)
          },
          label = {
            Text(
              text = date,
              modifier = Modifier.wrapContentWidth(),
              fontWeight = FontWeight.Normal,
              fontSize = 14.sp,
              lineHeight = 16.sp,
              textAlign = TextAlign.Center,
              letterSpacing = 0.4.sp,
            )
          },
          selected = date == selectedDate,
          border = if (date == selectedDate) {
            FilterChipDefaults.filterChipBorder(
              borderColor = MaterialTheme.colorScheme.primary,
              selectedBorderColor = Color.Transparent,
              borderWidth = 1.dp,
              selected = date == selectedDate,
              enabled = true,
            )
          } else {
            null
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = Color.White,
            containerColor = MaterialTheme.colorScheme.background,
            labelColor = GrayColor,
          )
        )
      }
    }
  }
}