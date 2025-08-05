package com.najeeb.movies.screens.addExpense.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.najeeb.movies.R
import com.najeeb.movies.components.CustomTextField
import com.najeeb.movies.components.DateTimePickerTextField
import com.najeeb.movies.core.HelperSize.defaultPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FromAddExpense(
  modifier: Modifier = Modifier,
  nameOnClick: () -> Unit
) {
  val sampleCompanies = listOf(
    Company(1, "Netflix", R.drawable.netflix_icon),
    Company(2, "Starbucks", R.drawable.starbucks_logo),
    Company(3, "Google", R.drawable.google_ads_icon),
    Company(4, "Paypal", R.drawable.paypal),
  )

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
      CompanyDropdown(
        companies = sampleCompanies,
        defaultSelectedCompany = sampleCompanies[0],
        onCompanySelected = { /* Handle selection */ },
      )
      Spacer(Modifier.height(20.dp))
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
  DateTimePickerTextField(
    selectedDateTime = selectedDate,
    onDateTimeSelected = { selectedDate = it },
  )
  Spacer(Modifier.height(defaultPadding.dp))
}
