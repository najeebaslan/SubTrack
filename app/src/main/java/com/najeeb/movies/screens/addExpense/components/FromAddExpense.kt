package com.najeeb.movies.screens.addExpense.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomTextField
import com.najeeb.movies.components.DatePickerTextField
import com.najeeb.movies.core.HelperSize.defaultPadding
import com.najeeb.movies.ui.theme.IconColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FromAddExpense(
  modifier: Modifier = Modifier,
  nameOnClick: () -> Unit
) {
  Card(
    shape = RoundedCornerShape(20.dp),
    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
    modifier = modifier
      .fillMaxWidth()
      .padding(horizontal = defaultPadding.dp)
      .shadow(
        shape = RoundedCornerShape(20.dp),
        elevation = 20.dp,
        ambientColor = Color.Black,
        spotColor = Color.Black
      )
  ) {
    Column(
      modifier = Modifier
        .padding(vertical = 30.dp, horizontal = defaultPadding.dp),
      horizontalAlignment = Alignment.Start
    ) {
      SectionTitle("NAME")
      NameCard(nameOnClick)

      SectionTitle("AMOUNT")
      AmountTextField()

      SectionTitle("DATE")
      PickDateButton()

      SectionTitle("INVOICE")
      InvoiceUploadButton()
    }
  }
}


@Composable
private fun SectionTitle(text: String) {
  Text(text, style = MaterialTheme.typography.bodyMedium)
  Spacer(Modifier.height(10.dp))
}

@Composable
private fun NameCard(
  onClick: () -> Unit
) {
  OutlinedCard(
    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
    modifier = Modifier
      .fillMaxWidth()

  ) {
    Row(
      modifier = Modifier
        .clickable { onClick }
        .padding(vertical = 10.dp, horizontal = 16.dp),
      horizontalArrangement = Arrangement.Start,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .shadow(
            shape = RoundedCornerShape(50.dp),
            elevation = 20.dp,
            ambientColor = Color.Black,
            spotColor = Color.Black
          ),
        painter = painterResource(id = R.drawable.netflix_icon),
        contentDescription = null,
      )
      Spacer(Modifier.width(16.dp))
      Text("Netflix", style = MaterialTheme.typography.bodyLarge)
      Spacer(Modifier.weight(1f))
      Icon(
        Icons.Default.KeyboardArrowDown,
        tint = IconColor,
        contentDescription = "Company name",
      )
    }
  }
  Spacer(Modifier.height(defaultPadding.dp))
}

@Composable
private fun AmountTextField() {
  var amount by remember { mutableStateOf("") }
  CustomTextField(
    enableClearButton = true,
    value = amount,
    onValueChange = { amount = it },
    hint = "\$ 48.00",
    hintStyle = MaterialTheme.typography.bodyLarge,
    modifier = Modifier.fillMaxWidth()
  )
  Spacer(Modifier.height(defaultPadding.dp))
}

@Composable
private fun PickDateButton() {
  var selectedDate by remember { mutableStateOf<String?>(null) }
  DatePickerTextField(
    label = "Birth Date",
    selectedDate = selectedDate,
    onDateSelected = { selectedDate = it }
  )
  Spacer(Modifier.height(defaultPadding.dp))
}
