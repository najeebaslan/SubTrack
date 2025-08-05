package com.najeeb.movies.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DateTimePickerTextField(
  modifier: Modifier = Modifier,
  selectedDateTime: String?,
  onDateTimeSelected: (String) -> Unit,
  showTime: Boolean = false,
  initialDate: Calendar? = null,
  minDate: Long? = null,
  maxDate: Long? = null,
) {
  val context = LocalContext.current
  val configuration = LocalConfiguration.current
  val currentLocale = configuration.locales[0]
  val isArabic = currentLocale.language == "ar"

  // Get current date-time
  val calendar = initialDate ?: Calendar.getInstance()
  val year = calendar.get(Calendar.YEAR)
  val month = calendar.get(Calendar.MONTH)
  val day = calendar.get(Calendar.DAY_OF_MONTH)

  // Format pattern based on language and time requirement
  val pattern = when {
    isArabic && showTime -> "dd/MM/yyyy HH:mm"
    isArabic -> "dd/MM/yyyy"
    showTime -> "MM/dd/yyyy hh:mm a"
    else -> "MM/dd/yyyy"
  }

  // Format current date-time
  val dateFormat = remember(currentLocale) {
    SimpleDateFormat(pattern, currentLocale)
  }
  val defaultDateTime = remember { dateFormat.format(calendar.time) }

  // DatePicker Dialog
  val datePickerDialog = remember {
    DatePickerDialog(
      context, { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
        calendar.set(selectedYear, selectedMonth, selectedDay)
        val formatted = dateFormat.format(calendar.time)
        onDateTimeSelected(formatted)
      },
      year,
      month,
      day
    ).apply {
      minDate?.let { datePicker.minDate = it }
      maxDate?.let { datePicker.maxDate = it }
//      setTitle(if (isArabic) selectDateLabel
//      else selectDateLabel)
    }
  }

  OutlinedTextField(
    value = selectedDateTime ?: defaultDateTime,
    onValueChange = {},
    modifier = modifier
      .fillMaxWidth()
      .clickable { datePickerDialog.show() },
    enabled = false,
    shape = RoundedCornerShape(10.dp),
    trailingIcon = {
      Icon(
        imageVector = Icons.Default.DateRange,
        contentDescription = "selectDateLabel",
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

// For time picker extension (optional)
@Composable
fun rememberTimePickerDialog(
  initialTime: Calendar = Calendar.getInstance(),
  onTimeSelected: (Calendar) -> Unit
): TimePickerDialog {
  val context = LocalContext.current
  return remember {
    TimePickerDialog(
      context,
      { _, hour, minute ->
        val newTime = Calendar.getInstance().apply {
          set(Calendar.HOUR_OF_DAY, hour)
          set(Calendar.MINUTE, minute)
        }
        onTimeSelected(newTime)
      },
      initialTime.get(Calendar.HOUR_OF_DAY),
      initialTime.get(Calendar.MINUTE),
      false // 24-hour format based on system settings
    )
  }
}