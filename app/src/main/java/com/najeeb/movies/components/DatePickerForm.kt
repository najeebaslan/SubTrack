package com.najeeb.movies.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Modifier
import java.util.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun DatePickerTextField(
  modifier: Modifier = Modifier,
  label: String = "Select Date",
  selectedDate: String?,
  onDateSelected: (String) -> Unit,
  @SuppressLint("DefaultLocale") dateFormat: (day: Int, month: Int, year: Int) -> String = { d, m, y ->
    String.format("%02d/%02d/%04d", d, m + 1, y)
  },
  initialDate: Calendar? = null,
  minDate: Long? = null,
  maxDate: Long? = null,
) {
  val context = LocalContext.current
  val calendar = initialDate ?: Calendar.getInstance()

  val year = calendar.get(Calendar.YEAR)
  val month = calendar.get(Calendar.MONTH)
  val day = calendar.get(Calendar.DAY_OF_MONTH)

  // Format today's date
  val todayFormatted = dateFormat(day, month, year)

  // Show picker with selected or today date
  val datePickerDialog = remember {
    DatePickerDialog(
      context, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
        val formatted = dateFormat(selectedDay, selectedMonth, selectedYear)
        onDateSelected(formatted)
      },
      year,
      month,
      day
    ).apply {
      minDate?.let { datePicker.minDate = it }
      maxDate?.let { datePicker.maxDate = it }
    }
  }

  OutlinedTextField(
    value = selectedDate ?: todayFormatted,
    onValueChange = {},
    modifier = modifier.fillMaxWidth()
      .clickable { datePickerDialog.show() },
    enabled = false,
    label = { Text(label) },
    shape = RoundedCornerShape(10.dp),
    trailingIcon = {
      Icon(
        imageVector = Icons.Default.DateRange,
        contentDescription = "Select Date"
      )
    },
    readOnly = true,
    colors = OutlinedTextFieldDefaults.colors(
      disabledBorderColor = MaterialTheme.colorScheme.outline,
      disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
      disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
    )
  )
}