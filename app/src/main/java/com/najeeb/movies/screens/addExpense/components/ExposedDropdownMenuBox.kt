package com.najeeb.movies.screens.addExpense.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.najeeb.movies.components.CustomTextField
import com.najeeb.movies.ui.theme.IconColor


data class Company(
  val id: Int,
  val name: String,
  val imageRes: Int // Assuming you're using resource IDs for images
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyDropdown(
  companies: List<Company>,
  defaultSelectedCompany: Company? = null,
  onCompanySelected: (Company) -> Unit,
  modifier: Modifier = Modifier
) {
  var expanded by remember { mutableStateOf(false) }
  var selectedCompany by remember {
    mutableStateOf(defaultSelectedCompany ?: companies.firstOrNull())
  }

  ExposedDropdownMenuBox(
    expanded = expanded,
    onExpandedChange = { expanded = !expanded },
    modifier = modifier
  ) {
    CustomTextField(
      enableClearButton = false,
      readOnly = true,
      trailingIcon = {
        Icon(
          Icons.Default.KeyboardArrowDown, null,
          tint = IconColor,
          modifier = modifier.rotate(if (expanded) 180f else 0f)
        )
      },
      modifier = Modifier
        .menuAnchor(MenuAnchorType.PrimaryEditable, true)
        .fillMaxWidth(),
      value = selectedCompany?.name ?: "Select a company",
      onValueChange = { },
      hintStyle = MaterialTheme.typography.bodyLarge,
      leadingIcon = selectedCompany?.let { company ->
        {
          Image(
            painter = painterResource(id = company.imageRes),
            contentDescription = null,
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
          )
        }
      },
    )


    ExposedDropdownMenu(
      containerColor = MaterialTheme.colorScheme.background,
      expanded = expanded,
      onDismissRequest = { expanded = false }
    ) {
      companies.forEach { company ->
        DropdownMenuItem(
          colors = MenuDefaults.itemColors(
            MaterialTheme.colorScheme.background
          ),
          text = {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Image(
                painter = painterResource(id = company.imageRes),
                contentDescription = null,
                modifier = Modifier
                  .size(24.dp)
                  .clip(CircleShape)
              )
              Text(text = company.name)
            }
          },
          onClick = {
            selectedCompany = company
            onCompanySelected(company)
            expanded = false
          }
        )
      }
    }
  }
}
